package br.edu.utfpr.crudmvcspring.model.mapper;

import br.edu.utfpr.crudmvcspring.model.dto.ProfessorDTO;
import br.edu.utfpr.crudmvcspring.model.dto.UniversityDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Professor;
import br.edu.utfpr.crudmvcspring.model.entity.University;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProfessorMapper {

    @Autowired
    private ModelMapper mapper;

    public ProfessorDTO toDto(Professor entity) {
        ProfessorDTO dto = mapper.map(entity, ProfessorDTO.class);
        return dto;
    }

    public Professor toEntity(ProfessorDTO dto, University university) {
        Professor entity = mapper.map(dto, Professor.class);
        entity.setUniversity(university);
        return entity;
    }

    public Professor toEntity(ProfessorDTO dto) {
        Professor entity = mapper.map(dto, Professor.class);
        return entity;
    }
}
