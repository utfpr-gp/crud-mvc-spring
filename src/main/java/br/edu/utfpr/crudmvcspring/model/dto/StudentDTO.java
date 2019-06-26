package br.edu.utfpr.crudmvcspring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDTO {

    private Long registration;

    @NotEmpty(message = "O gênero do aluno é obrigatório")
    private String gender;

    @NotEmpty(message = "O nome do aluno é obrigatório")
    @Pattern(regexp = "^(\\s?[A-ZÀ-Ú][a-zà-ú]+)+(\\s[a-zà-ú]*)?(\\s[A-ZÀ-Ú][a-zà-ú]*)+", message = "Insira o seu nome completo iniciando com letras maíusculas.")
    private String name;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Pattern(regexp = "^([12]\\d|3[01])/(0\\d|1[012])/\\d{4}$",
            message = "A data precisa estar formatada como dd/MM/yyyy")
    //@Past(message = "A data de nascimento não é válida")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private String birthDate;

    @NotEmpty(message = "O nome do curso é obrigatório.")
    @Length(min = 2, max = 100, message = "O nome do curso deve conter no mínimo 2 e máximo 100 caracteres.")
    private String course;

    @Email(message = "Insira um email válido.")
    @NotEmpty(message = "O email é obrigatório.")
    private String email;

    /**
     *
     * Usado pelo ModelMapper para transformar um DTO para entidade.
     * Precisa retornar Date ao invés de String para facilitar a conversão.
     * No DTO, a data é tratada como String, mas na Entity é tratada como Date.
     * @return
     */
    public Date getBirthDate() {
        Date d = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            d = format.parse(birthDate);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
