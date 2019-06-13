package br.edu.utfpr.crudmvcspring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @NotNull(message = "O campo de registro acadêmico é obrigatório.")
    private Long registration;

    @NotEmpty(message = "O nome não pode ser vazio")
    @Pattern(regexp = "^(\\s?[A-ZÀ-Ú][a-zà-ú]+)+(\\s[a-zà-ú]*)?(\\s[A-ZÀ-Ú][a-zà-ú]*)+", message = "Insira o seu nome completo iniciando com letras maíusculas.")
    private String name;

    private Date birthDate;

    @NotEmpty(message = "O nome do curso não pode ser vazio.")
    @Length(min = 2, max = 100, message = "O nome do curso de conter no mínimo 2 e máximo 100 caracteres.")
    private String course;

    @Email(message = "Insira um email válido.")
    @NotEmpty(message = "O email é obrigatório.")
    private String email;

}
