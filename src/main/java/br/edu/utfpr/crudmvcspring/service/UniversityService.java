package br.edu.utfpr.crudmvcspring.service;

import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.entity.University;
import br.edu.utfpr.crudmvcspring.model.repository.StudentRepository;
import br.edu.utfpr.crudmvcspring.model.repository.UniversityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public Optional<University> findById(Long id){
        return this.universityRepository.findById(id);
    }

    public University save(University entity){
        return universityRepository.save(entity);
    }

    public void delete(Long id){
        universityRepository.deleteById(id);
    }

    public List<University> findAll(){
        return this.universityRepository.findAll();
    }

    public void init() {
        University u1 = new University("Universidade Tecnológica Federal do Paraná", "UTFPR", "Guarapuava", "Paraná");
        University u2 = new University("Universidade Estadual do Centro-Oeste", "Unicentro", "Guarapuava", "Paraná");
        universityRepository.save(u1);
        universityRepository.save(u2);
    }
}
