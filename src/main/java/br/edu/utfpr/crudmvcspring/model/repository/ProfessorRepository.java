package br.edu.utfpr.crudmvcspring.model.repository;

import br.edu.utfpr.crudmvcspring.model.entity.Professor;
import br.edu.utfpr.crudmvcspring.model.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
