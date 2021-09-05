package br.edu.utfpr.crudmvcspring;

import br.edu.utfpr.crudmvcspring.service.StudentService;
import br.edu.utfpr.crudmvcspring.service.UniversityService;
import br.edu.utfpr.crudmvcspring.util.WizardSessionUtil;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;

@SpringBootApplication
@ServletComponentScan
public class CrudMvcSpringApplication {

	@Autowired
	private StudentService studentService;

	@Autowired
	private UniversityService universityService;

	public static void main(String[] args) {
		SpringApplication.run(CrudMvcSpringApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/***
	 *
	 * Configurações de data, numeração, moeda do Brasil
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			//inicializa a base de dados
			studentService.init();
			universityService.init();

//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			InputStream is = new FileInputStream(ResourceUtils.getFile("classpath:Roboto-Regular.ttf"));
//			java.awt.Font font;
//			try {
//				font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
//				ge.registerFont(font);
//			} catch (FontFormatException e) {
//				e.printStackTrace();
//			}
		};
	}

//	@Bean
//	public Cloudinary cloudinary(){
//		return new Cloudinary(ObjectUtils.asMap(
//				"cloud_name","dgueb0wir",
//				"api_key", "546318655587864",
//				"api_secret", "UPEpuVA_PWlah9B5BrkZMx7E5VE"
//		));
//	}

	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name","imovelandia",
				"api_key", "411178834613179",
				"api_secret", "dXKqah7iauuraHYG2tghyK6HPYk"
		));
	}

}
