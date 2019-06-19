package br.edu.utfpr.crudmvcspring.model.dto;

import br.edu.utfpr.crudmvcspring.model.entity.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDTO {

    @NotNull(message = "O campo de registro acadêmico é obrigatório.")
    private Long registration;

    @NotEmpty(message = "O gênero do aluno é obrigatório")
    private String gender;

    @NotEmpty(message = "O nome do aluno é obrigatório")
    @Pattern(regexp = "^(\\s?[A-ZÀ-Ú][a-zà-ú]+)+(\\s[a-zà-ú]*)?(\\s[A-ZÀ-Ú][a-zà-ú]*)+", message = "Insira o seu nome completo iniciando com letras maíusculas.")
    private String name;

    @NotEmpty(message = "A data de nascimento é obrigatória")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @NotEmpty(message = "O nome do curso é obrigatório.")
    @Length(min = 2, max = 100, message = "O nome do curso de conter no mínimo 2 e máximo 100 caracteres.")
    private String course;

    @Email(message = "Insira um email válido.")
    @NotEmpty(message = "O email é obrigatório.")
    private String email;

}
