package br.edu.utfpr.crudmvcspring.service;

import br.edu.utfpr.crudmvcspring.model.entity.Professor;
import br.edu.utfpr.crudmvcspring.model.entity.University;
import br.edu.utfpr.crudmvcspring.model.repository.ProfessorRepository;
import br.edu.utfpr.crudmvcspring.model.repository.UniversityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor save(Professor entity){
        return professorRepository.save(entity);
    }

    public void delete(Long id){
        professorRepository.deleteById(id);
    }

    public List<Professor> findAll(){
        return this.professorRepository.findAll();
    }

    public void init() {

    }
}
