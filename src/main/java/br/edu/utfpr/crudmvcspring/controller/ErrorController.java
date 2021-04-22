package br.edu.utfpr.crudmvcspring.controller;

import br.edu.utfpr.crudmvcspring.exception.InvalidParamsException;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/errors")
public class ErrorController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public String testError(@PathVariable("id") int id) throws Exception{
        //deliberadamente lança alguns tipos de exceptions
        if(id==1){
            throw new InvalidParamsException("Parâmetros inválidos, id " + id);
        }else if(id==2){
            throw new SQLException("SQLException, id="+id);
        }else if(id==3){
            throw new IOException("IOException, id="+id);
        }else if(id==10){
            //vai gerar exception no bd pois o nome dos estudante não pode ser nulo
            Student s = new Student();
            studentService.save(s);
            return "alunos";
        }else {
            throw new Exception("Exception genérico, id="+id);
        }
    }
}
