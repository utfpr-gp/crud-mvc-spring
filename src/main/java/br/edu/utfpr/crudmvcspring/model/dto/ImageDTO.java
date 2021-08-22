package br.edu.utfpr.crudmvcspring.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class ImageDTO {
    @NotBlank
    private String name;
    private MultipartFile imageFile;
    private MultipartFile[] imageFiles;
}