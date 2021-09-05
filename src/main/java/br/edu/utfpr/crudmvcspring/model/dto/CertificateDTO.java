package br.edu.utfpr.crudmvcspring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CertificateDTO{

    @Email(message = "Insira um email válido.")
    @NotEmpty(message = "O email é obrigatório.")
    private String email;

    @NotEmpty(message = "O ano de conclusão é obrigatório")
    @Pattern(regexp = "\\d{4}", message = "O ano precisa conter 4 dígitos")
    private String year;
}
