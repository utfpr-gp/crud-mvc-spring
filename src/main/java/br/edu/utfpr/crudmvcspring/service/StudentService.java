package br.edu.utfpr.crudmvcspring.service;

import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student entity){
        return studentRepository.save(entity);
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }

    public List<Student> findAll(){
        return this.studentRepository.findAll();
    }

    public Page<Student> findAll(PageRequest pageRequest){
        return this.studentRepository.findAll(pageRequest);
    }

    public Optional<Student> findById(Long id){
        return this.studentRepository.findById(id);
    }
}
