package br.edu.utfpr.crudmvcspring.model.repository;

import br.edu.utfpr.crudmvcspring.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("select s from Student s where s.name like %?1")
    List<Student> findByNameEndsWith(String name);

    @Query("select s from Student s where s.name like %:name")
    Page<Student> findByNameEndsWith(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM students WHERE course = ?1", nativeQuery = true)
    List<Student> findByCourse(String course);

    List<Student> findByCourseAndName(@Param("course") String course, @Param("name") String name);
    List<Student> findByBirthDateBetween(Date d1, Date d2);
    List<Student> findByCourseOrderByName(String course);
    List<Student> findByGenderIsNotNull();
    List<Student> findByNameLike(String name);


}
