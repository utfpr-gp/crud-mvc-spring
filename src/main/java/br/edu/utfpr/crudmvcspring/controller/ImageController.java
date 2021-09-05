package br.edu.utfpr.crudmvcspring.controller;


import br.edu.utfpr.crudmvcspring.exception.InvalidParamsException;
import br.edu.utfpr.crudmvcspring.model.dto.ImageDTO;
import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.service.StudentService;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cloudinary.Cloudinary;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
//@CommonsLog
@RequestMapping("/imagens")
@Controller
public class ImageController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showIndex() {
        return "form-upload";
    }

    @PostMapping
    @PermitAll
    public ModelAndView save(@Validated ImageDTO dto, BindingResult errors, RedirectAttributes redirectAttributes) throws IOException {

        if(dto.getImageFile() == null){
            throw new InvalidParamsException("Nenhuma imagem foi enviada.");
        }
        if(!isValidateImage(dto.getImageFile())){
            throw new InvalidParamsException("A imagem não está em um formato suportado.");
        }

        Path path = Files.createTempFile("spring", dto.getImageFile().getOriginalFilename());
        System.out.println("Caminho temporário: " + path);

        File file = path.toFile();
        dto.getImageFile().transferTo(file);

        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "resources"));

        // remove o arquivo temporário na máquina
        file.delete();

        redirectAttributes.addFlashAttribute("imageURL", uploadResult.get("url"));
        redirectAttributes.addFlashAttribute("name", dto.getName());

        //redirecionamento para a rota
        return new ModelAndView("redirect:imagens");


    }



    public boolean isValidateImage(MultipartFile image){
        List<String> contentTypes = Arrays.asList("image/png", "image/jpg", "image/jpeg");

        for(int i = 0; i < contentTypes.size(); i++){
            if(image.getContentType().toLowerCase().startsWith(contentTypes.get(i))){
                return true;
            }
        }

        return false;
    }
}
