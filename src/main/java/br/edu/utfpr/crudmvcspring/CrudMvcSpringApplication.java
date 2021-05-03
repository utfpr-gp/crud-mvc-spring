package br.edu.utfpr.crudmvcspring;

import br.edu.utfpr.crudmvcspring.service.StudentService;
import br.edu.utfpr.crudmvcspring.service.UniversityService;
import br.edu.utfpr.crudmvcspring.util.WizardSessionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

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
		};
	}

}
