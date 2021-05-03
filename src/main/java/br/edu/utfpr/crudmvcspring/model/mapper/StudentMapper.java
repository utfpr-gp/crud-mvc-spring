package br.edu.utfpr.crudmvcspring.model.mapper;

import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Slf4j
@Component
public class StudentMapper {

    @Autowired
    private ModelMapper mapper;

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd/MM/yyyy");

    public StudentDTO toDto(Student entity) {
        StudentDTO dto = mapper.map(entity, StudentDTO.class);
        dto.setBirthDate(this.dateFormat.format(entity.getBirthDate()));
        return dto;
    }

    public StudentDTO toResponseDto(Student entity) {
        StudentDTO dto = mapper.map(entity, StudentDTO.class);
        dto.setGender(entity.getGender() != null ? entity.getGender().getName() : "");
        dto.setBirthDate(this.dateFormat.format(entity.getBirthDate()));
        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        Student entity = mapper.map(dto, Student.class);
        return entity;
    }


}
