package br.edu.utfpr.crudmvcspring.model.repository;

import br.edu.utfpr.crudmvcspring.model.entity.Student;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;
import java.util.*;

//import static org.junit.Assert.*;

/**
 *
 * @author ronifabio
 */
@SpringBootTest
@ActiveProfiles("test")
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    public StudentRepositoryTest() {
    }

    @BeforeEach
    public void setUp() {
        Student s1 = new Student("João de Souza", "Eng. Mecânica", "joaotest@email.com", new GregorianCalendar(1984, Calendar.OCTOBER, 14).getTime());
        Student s2 = new Student("José de Souza", "TSI", "josetest@email.com", new GregorianCalendar(1990, Calendar.JANUARY, 1).getTime());
        Student s3 = new Student("Júlio de Souza", "TSI", "juliotest@email.com", new GregorianCalendar(2005, Calendar.JANUARY, 10).getTime());
        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
    }

    @AfterEach
    public void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar uma lista preenchida de estudantes")
    public void findByNameEndsWith() {
        List<Student> students = studentRepository.findByNameEndsWith("Souza");
        Assertions.assertFalse(students.isEmpty());
        Assertions.assertEquals(students.size(), 3);
    }

    @Test
    @Disabled("Ainda não implementado")
    public void findByNameEndsWithPageable() {
        Page<Student> page = studentRepository.findByNameEndsWith("Souza", PageRequest.of(1, 20));
        Assertions.assertNotEquals(page.getTotalElements(), 0);
        Assertions.assertEquals(page.getTotalElements(), 3);
    }

    @Test
    void findByCourse() {
        List<Student> students = studentRepository.findByCourse("TSI");
        Assertions.assertFalse(students.isEmpty());
        Assertions.assertEquals(students.size(), 2);
    }

    @Test
    public void delete() {
        Student student = new Student("Tomaz Leite", "Eng. Mecânica", "tomaz@email.com", new GregorianCalendar(1984, Calendar.OCTOBER, 14).getTime());
        this.studentRepository.save(student);
        student = studentRepository.getOne(student.getRegistration());
        long id = student.getRegistration();
        this.studentRepository.delete(student);
        //retorna Optional.empty
        Assertions.assertEquals(studentRepository.findById(id), Optional.empty());
    }

    /**
     *
     * Exemplo de atualização de dados.
     *
     */
    @Test
    @DisplayName("Atualização de nome não deve ser aceita porque o campo não é atualizável")
    public void updateNameThatIsNotUpdatableShouldNotWork() {
        Student student = new Student("Tomaz Leite", "Eng. Mecânica", "tomaz@email.com", new GregorianCalendar(1984, Calendar.OCTOBER, 14).getTime());
        this.studentRepository.save(student);
        student.setName("Ronaldo Gaúcho");
        student.setCourse("Engenharia Civil");

        //retorna o objeto salvo
        student = this.studentRepository.save(student);

        //forma mais correta é fazer uma nova busca pelo objeto
        Optional<Student> oStudent = studentRepository.findById(student.getRegistration());
        Assertions.assertNotEquals(oStudent, Optional.empty());
        Assertions.assertNotEquals(oStudent.get().getName(), "Ronaldo Gaúcho");
        Assertions.assertEquals(oStudent.get().getCourse(),"Engenharia Civil");
    }

    /**
     *
     * Tentativa de salvar dois estudantes com o mesmo CPF.
     * CPF é unique no Banco de dados.
     * Foi gerado DataIntegrityException pelo banco de dados.
     *
     */
    @Test
    public void insertWhenEmailIsNonUniqueShouldThrowDataIntegrityException(){
        Assertions.assertThrows(DataIntegrityViolationException.class, ()->{
            Student s1 = new Student("Tomaz Leite", "Eng. Mecânica", "tomaz@email.com", new GregorianCalendar(1984, Calendar.OCTOBER, 14).getTime());
            Student s2 = new Student("Bieber Leite", "Eng. Mecânica", "tomaz@email.com", new GregorianCalendar(1984, Calendar.OCTOBER, 14).getTime());
            studentRepository.save(s1);
            studentRepository.save(s2);
        });
    }

    @Test
    void insertWithNameAndEmailIsNullShouldThrowDataIntegrityViolationException(){
        Assertions.assertThrows(DataIntegrityViolationException.class, ()->{
            Student s1 = new Student(null, "Eng. Mecânica", null, new GregorianCalendar(1984, Calendar.OCTOBER, 14).getTime());
            studentRepository.save(s1);
        });
    }
}
