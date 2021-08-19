package br.edu.utfpr.crudmvcspring.controller;


import br.edu.utfpr.crudmvcspring.model.dto.SubDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequestMapping("/disciplinas")
@Controller
public class SubjectController {

    //public static final Logger log =  LoggerFactory.getLogger(IndexController.class);

    @GetMapping
    public String showSubjects() {
        return "choose-subjects";
    }

    @PostMapping
    public ModelAndView saveSubjects(SubDTO dto, BindingResult errors) {
        ModelAndView mv = new ModelAndView("choose-subjects");
        mv.addObject("subjects", dto.getSubjects());
        mv.addObject("periods", dto.getPeriods());
        return mv;
    }
}
