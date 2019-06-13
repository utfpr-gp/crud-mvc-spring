package br.edu.utfpr.crudmvcspring.controller;

import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.mapper.StudentMapper;
import br.edu.utfpr.crudmvcspring.model.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        log.info("Mostrando o formulário de cadastro de aluno");
        ModelAndView mv = new ModelAndView("form-students", "student", new StudentDTO());
        return mv;
    }

    @PostMapping
    public String save(@ModelAttribute("student") StudentDTO  dto){

        log.info("Persistindo o aluno com email {}", dto.getEmail());


        return "";
    }

//    @GetMapping
//    public ModelAndView findAll(){
//        List<Student> students = studentService.findAll();
//
//        //converte para DTO
//        List<StudentDTO> studentDTOS = students.stream().map(t -> studentMapper.toDto(t)).collect(Collectors.toList());
//
//        //encaminha para redesenhar a página
//        ModelAndView mv = new ModelAndView("/", "students", students);
//        return mv;
//    }
}
