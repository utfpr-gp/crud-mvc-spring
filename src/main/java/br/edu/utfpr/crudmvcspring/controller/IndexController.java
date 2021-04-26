package br.edu.utfpr.crudmvcspring.controller;


import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
//@CommonsLog
@RequestMapping("/")
@Controller
public class IndexController {

    //public static final Logger log =  LoggerFactory.getLogger(IndexController.class);

    @GetMapping
    public String showIndex() {
        log.debug("Mostrando o index");
        return "index";
    }


}
