package br.edu.utfpr.crudmvcspring.service;

import br.edu.utfpr.crudmvcspring.model.entity.GenderEnum;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.repository.StudentRepository;
import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    Cloudinary cloudinary;

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

    public Page<Student> findMasculine(GenderEnum genderEnum, PageRequest pageRequest){
        return this.studentRepository.findByGender(genderEnum, pageRequest);
    }

    public Page<Student> findAll(Pageable pageRequest){
        return this.studentRepository.findAll(pageRequest);
    }

    public Optional<Student> findById(Long id){
        return this.studentRepository.findById(id);
    }

    public List<Student> findByCourseAndName(String course, String name){
        return this.studentRepository.findByCourseAndName(course, name);
    }

    public List<Student> findByBirthDateBetween(Date d1, Date d2){
        return this.studentRepository.findByBirthDateBetween(d1, d2);
    }

    public List<Student> findByCourseOrderByName(String course){
        return studentRepository.findByCourseOrderByName(course);
    }

    public List<Student> findByGenderIsNotNull(){
        return studentRepository.findByGenderIsNotNull();
    }

    public List<Student> findByNameLike(String name){
        return studentRepository.findByNameLike(name);
    }

    public List<Student> findByCourse(String course){
        return studentRepository.findByCourse(course);
    }

    public Page<Student> findByNameEndsWith(@Param("name") String name, Pageable pageable){
        return studentRepository.findByNameEndsWith(name, pageable);
    }

    public Optional<Student> findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public void init() {
        Student s1 = new Student("João de Sá Pato", "Eng. Mecânica", "joao@email.com", new Date());
        Student s2 = new Student("José de Sá Pato", "Eng. Mecânica", "jose@email.com", new Date());
        Student s3 = new Student("Júlio de Sá Pato", "Eng. Mecânica", "julio@email.com", new Date());

        List<Student> students = Arrays.asList(s1, s2, s3);
        log.debug("Inicializando o BD com {} objetos da classe {}", students.size(), Student.class.toString());
        studentRepository.saveAll(students);
    }
}
