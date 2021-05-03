package br.edu.utfpr.crudmvcspring.model.mapper;

import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.dto.UniversityDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.entity.University;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Slf4j
@Component
public class UniversityMapper {

    @Autowired
    private ModelMapper mapper;

    public UniversityDTO toDto(University entity) {
        UniversityDTO dto = mapper.map(entity, UniversityDTO.class);
        return dto;
    }

    public University toEntity(UniversityDTO dto) {
        University entity = mapper.map(dto, University.class);
        return entity;
    }
}
