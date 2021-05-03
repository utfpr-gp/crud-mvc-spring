package br.edu.utfpr.crudmvcspring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO implements Serializable {

    @CPF(message = "O CPF não é válido.", groups = ProfessorCPFGroupValidation.class)
    @NotBlank(message = "O CPF é obrigatório.", groups = ProfessorCPFGroupValidation.class)
    private String cpf;

    @NotBlank(message = "O nome não pode ser vazio.", groups = ProfessorNameGroupValidation.class)
    private String name;

    @NotBlank(message = "O departamento é obrigatório.", groups = ProfessorDepartmentGroupValidation.class)
    private String department;

    @NotNull(message = "Você precisa selecionar uma universidade.", groups = ProfessorDepartmentGroupValidation.class)
    private Long universityId;

    private String error;

    public interface ProfessorCPFGroupValidation {
    }
    public interface ProfessorNameGroupValidation {
    }
    public interface ProfessorDepartmentGroupValidation {
    }
}
