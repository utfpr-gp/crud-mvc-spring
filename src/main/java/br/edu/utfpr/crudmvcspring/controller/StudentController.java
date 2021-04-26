package br.edu.utfpr.crudmvcspring.controller;

import br.edu.utfpr.crudmvcspring.exception.InvalidParamsException;
import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.mapper.StudentMapper;
import br.edu.utfpr.crudmvcspring.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
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
        log.debug("Mostrando o formulário de cadastro de aluno com lista de alunos");
        ModelAndView mv = new ModelAndView("form-students");
        List<Student> students = studentService.findAll();

        //lista de alunos
        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        mv.addObject("students", studentDTOs);

        return mv;
    }

    @GetMapping
    public ModelAndView listStudents() {
        log.debug("Lista de alunos");
        ModelAndView mv = new ModelAndView("list-students");
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
    public ModelAndView save(@Validated StudentDTO  dto, BindingResult errors, RedirectAttributes redirectAttributes){

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

        log.debug("Persistindo o aluno {} com email {}", dto.getName(), dto.getEmail());
        log.debug("Persistindo o DTO {}", dto);

        Student student = studentMapper.toEntity(dto);
        log.debug("Persistindo o entidade {}", student);
        studentService.save(student);

        //prepara a lista de alunos
        List<Student> students = studentService.findAll();

        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        //redirectAttributes.addFlashAttribute("students", studentDTOs);
        redirectAttributes.addFlashAttribute("msg", "Aluno salvo com sucesso!");

        //redirecionamento para a rota
        return new ModelAndView("redirect:alunos");
    }

    @GetMapping("/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") Long id) {

        log.debug("Mostrando o formulário de edição de aluno com lista de alunos");
        ModelAndView mv = new ModelAndView("form-students");

        if(id < 0){
            throw new InvalidParamsException("O identificador não pode ser negativo.");
        }

        Optional<Student> oStudent = studentService.findById(id);

        if(!oStudent.isPresent()){
            throw new EntityNotFoundException("O aluno não foi encontrado pelo id informado.");
        }

        StudentDTO studentDTO = studentMapper.toResponseDto(oStudent.get());
        mv.addObject("dto", studentDTO);
        return mv;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.debug("Removendo um aluno com id {}", id);
        Optional<Student> o = this.studentService.findById(id);

        if (!o.isPresent()) {
            throw new EntityNotFoundException("Erro ao remover, registro não encontrado para o id " + id);
        }

        this.studentService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Aluno removido com sucesso!");
        return "redirect:/alunos";
    }


}
