package br.edu.utfpr.crudmvcspring.controller;


import br.edu.utfpr.crudmvcspring.model.dto.ProfessorDTO;
import br.edu.utfpr.crudmvcspring.model.dto.UniversityDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Professor;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.entity.University;
import br.edu.utfpr.crudmvcspring.model.mapper.ProfessorMapper;
import br.edu.utfpr.crudmvcspring.model.mapper.UniversityMapper;
import br.edu.utfpr.crudmvcspring.service.ProfessorService;
import br.edu.utfpr.crudmvcspring.service.UniversityService;
import br.edu.utfpr.crudmvcspring.util.WizardSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/professores")
@SessionAttributes("wizard")
public class ProfessorController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    UniversityMapper universityMapper;

    @Autowired
    ProfessorMapper professorMapper;

    @Autowired
    private WizardSessionUtil<ProfessorDTO> wizardSessionUtil;

    @Autowired
    private SmartValidator validator;

    @GetMapping
    public String showWizard(@RequestParam(value = "passo", required = false, defaultValue = "1") Long step,
                             HttpSession httpSession,
                             Model model) {
        log.debug("Mostrando o passo {}", step);

        if(step < 1 || step > 4){
            step = 1L;
        }

        ProfessorDTO dto = wizardSessionUtil.getWizardState(httpSession, ProfessorDTO.class);
        model.addAttribute("dto", dto);

        if(step == 3L){
            List<University> universities = universityService.findAll();
            List<UniversityDTO> universityDTOs = universities.stream()
                    .map(u -> universityMapper.toDto(u))
                    .collect(Collectors.toList());
            model.addAttribute("universityDTOs", universityDTOs);
        }

        return "professors/wizard-step-" + step;
    }

    @PostMapping("/passo-1")
    public String saveFormCPF(HttpSession httpSession, @Validated(ProfessorDTO.ProfessorCPFGroupValidation.class) ProfessorDTO dto, BindingResult errors, RedirectAttributes redirectAttributes, Model model){

        //verifica os erros de validação
        if(errors.hasErrors()){
            model.addAttribute("dto", dto);
            model.addAttribute("errors", errors.getAllErrors());
            return "professors/wizard-step-1";
        }
        //persiste na sessão
        ProfessorDTO sessionDTO = wizardSessionUtil.getWizardState(httpSession, ProfessorDTO.class);
        sessionDTO.setCpf(dto.getCpf());

        log.debug("Passo 1 {}", sessionDTO);

        return "redirect:/professores?passo=2";

    }

    @PostMapping("/passo-2")
    public String saveFormName(HttpSession httpSession, @Validated(ProfessorDTO.ProfessorNameGroupValidation.class) ProfessorDTO dto, BindingResult errors, Model model){
        //verifica os erros de validação
        if(errors.hasErrors()){
            model.addAttribute("dto", dto);
            model.addAttribute("errors", errors.getAllErrors());
            return "professors/wizard-step-2";
        }
        //persiste na sessão
        ProfessorDTO sessionDTO = wizardSessionUtil.getWizardState(httpSession, ProfessorDTO.class);
        sessionDTO.setName(dto.getName());

        log.debug("Passo 2 {}", sessionDTO);

        return "redirect:/professores?passo=3";
    }

    @PostMapping("/passo-3")
    public String saveFormUniversity(HttpSession httpSession, @Validated(ProfessorDTO.ProfessorDepartmentGroupValidation.class) ProfessorDTO dto, BindingResult errors, Model model, RedirectAttributes redirectAttributes, SessionStatus status){
        Optional<University> oUniversity = null;
        if(dto.getUniversityId() != null) {
            oUniversity = universityService.findById(dto.getUniversityId());
            if (!oUniversity.isPresent()) {
                errors.rejectValue("universidade", "A universidade não foi encontrada!");
            }
        }

        //verifica os erros de validação
        if(errors.hasErrors()){
            model.addAttribute("dto", dto);
            model.addAttribute("errors", errors.getAllErrors());

            //preenche o select de universidades
            List<University> universities = universityService.findAll();
            List<UniversityDTO> universityDTOs = universities.stream()
                    .map(u -> universityMapper.toDto(u))
                    .collect(Collectors.toList());
            model.addAttribute("universityDTOs", universityDTOs);
            return "professors/wizard-step-3";
        }

        //persiste na sessão
        ProfessorDTO sessionDTO = wizardSessionUtil.getWizardState(httpSession, ProfessorDTO.class);
        sessionDTO.setDepartment(dto.getDepartment());
        sessionDTO.setUniversityId(dto.getUniversityId());

        //simula um erro em passos anteriores
        if(dto.getError() != null && dto.getError().equals("on")) {
            sessionDTO.setName(null);
        }

        //valida o dto completo
        validator.validate(sessionDTO, errors, new Class[]{ProfessorDTO.ProfessorCPFGroupValidation.class, ProfessorDTO.ProfessorNameGroupValidation.class, ProfessorDTO.ProfessorDepartmentGroupValidation.class});

        //se houver algum erro, apresenta a página final
        if(errors.hasErrors()){
            model.addAttribute("dto", dto);
            model.addAttribute("errors", errors.getAllErrors());
            return "professors/wizard-step-4";
        }

        Professor professor = professorMapper.toEntity(sessionDTO, oUniversity.get());
        professorService.save(professor);

        redirectAttributes.addFlashAttribute("msg", "Professor salvo com sucesso!");

        //limpa os atributos da sessão
        status.setComplete();
        return "redirect:/professores?passo=4";
    }
}
