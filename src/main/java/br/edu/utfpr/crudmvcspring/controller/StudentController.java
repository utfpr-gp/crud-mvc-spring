package br.edu.utfpr.crudmvcspring.controller;

import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.mapper.StudentMapper;
import br.edu.utfpr.crudmvcspring.model.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/alunos")
@Controller
public class StudentController {

    public static final Logger log =
            LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping
    public ModelAndView showForm() {
        log.info("Mostrando o formulário de cadastro de aluno com lista de alunos");
        ModelAndView mv = new ModelAndView("form-students");
        List<Student> students = studentService.findAll();

        //lista de alunos
        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        mv.addObject("students", studentDTOs);

        return mv;
    }

    /**
     *
     * Post com encaminhamento em caso de erro e redirecionamento em caso de sucesso.
     *
     * @param dto
     * @param errors
     * @param redirectAttributes
     * @return
     */
    @PostMapping
    public ModelAndView save(@Validated StudentDTO  dto, Errors errors, RedirectAttributes redirectAttributes){

        //imprime o código de erro e o nome do atributo
        for(FieldError e: errors.getFieldErrors()){
            log.info(e.getField() + " -> " + e.getCode());
        }
        //verifica os erros de validação
        if(errors.hasErrors()){
            ModelAndView mv = new ModelAndView("form-students");
            //salva o DTO para manter tais dados em caso de erro
            mv.addObject("dto", dto);
            //salva os erros para serem apresentados
            mv.addObject("errors", errors.getAllErrors());
            //encaminhamento para a visão
            return mv;
        }

        log.info("Persistindo o aluno com email {}", dto.getEmail());
        log.info("Persistindo o DTO {}", dto);

        Student student = studentMapper.toEntity(dto);
        studentService.save(student);

        //prepara a lista de alunos
        List<Student> students = studentService.findAll();

        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        redirectAttributes.addFlashAttribute("students", studentDTOs);
        redirectAttributes.addFlashAttribute("msg", "Aluno salvo com sucesso!");

        //redirecionamento para a rota
        return new ModelAndView("redirect:alunos");
    }


}
