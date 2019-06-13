package br.edu.utfpr.crudmvcspring.model.mapper;

import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    @Autowired
    private ModelMapper mapper;

    public StudentDTO toDto(Student entity) {
        StudentDTO dto = mapper.map(entity, StudentDTO.class);
        return dto;
    }

    public Student toEntity(StudentDTO dto) throws ParseException {
        Student entity = mapper.map(dto, Student.class);
        return entity;
    }
}
