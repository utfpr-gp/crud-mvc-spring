package br.edu.utfpr.crudmvcspring.model.repository;

import br.edu.utfpr.crudmvcspring.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
