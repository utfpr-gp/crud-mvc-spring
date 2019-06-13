package br.edu.utfpr.crudmvcspring.model.service;

import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student entity){
        return studentRepository.save(entity);
    }

    public List<Student> findAll(){
        return this.studentRepository.findAll();
    }
}
