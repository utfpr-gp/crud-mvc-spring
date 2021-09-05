package br.edu.utfpr.crudmvcspring.controller;

import br.edu.utfpr.crudmvcspring.exception.InvalidParamsException;
import br.edu.utfpr.crudmvcspring.model.dto.CertificateDTO;
import br.edu.utfpr.crudmvcspring.model.dto.ImageDTO;
import br.edu.utfpr.crudmvcspring.model.entity.GenderEnum;
import br.edu.utfpr.crudmvcspring.service.EmailService;
import br.edu.utfpr.crudmvcspring.util.pagination.PaginationDTO;
import br.edu.utfpr.crudmvcspring.model.dto.StudentDTO;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.mapper.StudentMapper;
import br.edu.utfpr.crudmvcspring.service.StudentService;
import br.edu.utfpr.crudmvcspring.util.pagination.PaginationUtil;
import br.edu.utfpr.crudmvcspring.util.tabs.TabDTO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.fop.activity.ContainerUtil;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.configuration.Configuration;
import org.apache.fop.configuration.ConfigurationException;
import org.apache.fop.configuration.DefaultConfigurationBuilder;
import org.apache.fop.svg.PDFTranscoder;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.security.PermitAll;
import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/alunos")
@Controller
public class StudentController {

    public static final Logger log =
            LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private EmailService emailService;

    @Value("${svg.certificate.template}")
    private String svgCertificateTemplate;

    @GetMapping
    public ModelAndView showForm() {
        log.debug("Mostrando o formulário de cadastro de aluno com lista de alunos");
        ModelAndView mv = new ModelAndView("form-students");
        List<Student> students = studentService.findAll();

        //lista de alunos
        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());

        mv.addObject("students", studentDTOs);

        return mv;
    }

    @GetMapping("/filtros/{id}")
    public ModelAndView testQueryMethods(@PathVariable("id") Long id) {

        ModelAndView mv = new ModelAndView("list-students");
        List<Student> students = null;
        if(id  == 1L){
            students = studentService.findByCourseAndName("TSI", "João Brasil");
        }
        else if(id == 2){
            students = studentService.findByBirthDateBetween(new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(1989, Calendar.JANUARY, 1).getTime());
        }
        else if(id ==3){
            students = studentService.findByCourseOrderByName("TSI");
        }
        else if(id == 4){
            students = studentService.findByGenderIsNotNull();
        }
        else if(id == 5){
            students = studentService.findByNameLike("%R%");
        }
        else if(id == 6){
            students = studentService.findByCourse("TSI");
        }
        else if(id == 7){
            PageRequest pageRequest = PageRequest.of(0, 5);
            students = studentService.findByNameEndsWith("Brasil", pageRequest).getContent();
        }

        log.debug("Estudantes {} ", students.size() );

        //lista de alunos
        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        mv.addObject("students", studentDTOs);

        return mv;
    }

    @GetMapping("/paginacao")
    public ModelAndView listStudents(HttpServletRequest request,
                                     @RequestParam(value = "pag", defaultValue = "1") int page,
                                     @RequestParam(value = "siz", defaultValue = "3") int size,
                                     @RequestParam(value = "ord", defaultValue = "name") String order,
                                     @RequestParam(value = "dir", defaultValue = "ASC") String direction) {
        log.debug("Lista de alunos");

        ModelAndView mv = new ModelAndView("list-students");
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.Direction.valueOf(direction), order);
        Page<Student> studentPage = studentService.findAll(pageRequest);

        //lista de alunos
        List<StudentDTO> studentDTOs = studentPage.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        mv.addObject("students", studentDTOs);

        PaginationDTO paginationDTO = PaginationUtil.getPaginationDTO(studentPage);
        mv.addObject("pagination", paginationDTO);

        return mv;
    }

    @GetMapping("/paginacao-ordenada-id")
    public ModelAndView listByIdStudents(HttpServletRequest request,
                                     @RequestParam(value = "pag", defaultValue = "1") int page,
                                     @RequestParam(value = "siz", defaultValue = "3") int size,
                                     @RequestParam(value = "ord", defaultValue = "registration") String order,
                                     @RequestParam(value = "dir", defaultValue = "ASC") String direction) {
        log.debug("Lista de alunos");

        ModelAndView mv = new ModelAndView("list-students");
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(order).ascending().and(Sort.by("name")));
        Page<Student> studentPage = studentService.findAll(pageRequest);

        //lista de alunos
        List<StudentDTO> studentDTOs = studentPage.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        mv.addObject("students", studentDTOs);

        PaginationDTO paginationDTO = PaginationUtil.getPaginationDTO(studentPage, "/alunos/paginacao-ordenada-id");
        mv.addObject("pagination", paginationDTO);

        return mv;
    }

    /**
     *
     * Post com encaminhamento em caso de erro e redirecionamento em caso de sucesso.
     *
     * @param dto
     * @param errors
     * @param redirectAttributes
     * @return
     */
    @PostMapping
    public ModelAndView save(@Validated StudentDTO  dto, BindingResult errors, RedirectAttributes redirectAttributes){

        //imprime o código de erro e o nome do atributo
        for(FieldError e: errors.getFieldErrors()){
            log.info(e.getField() + " -> " + e.getCode());
        }
        //verifica os erros de validação
        if(errors.hasErrors()){
            ModelAndView mv = new ModelAndView("form-students");
            //salva o DTO para manter tais dados em caso de erro
            mv.addObject("dto", dto);
            //salva os erros para serem apresentados
            mv.addObject("errors", errors.getAllErrors());
            //encaminhamento para a visão
            return mv;
        }

        log.debug("Persistindo o aluno {} com email {}", dto.getName(), dto.getEmail());
        log.debug("Persistindo o DTO {}", dto);

        Student student = studentMapper.toEntity(dto);
        log.debug("Persistindo o entidade {}", student);
        studentService.save(student);

        //prepara a lista de alunos
        List<Student> students = studentService.findAll();

        List<StudentDTO> studentDTOs = students.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        //redirectAttributes.addFlashAttribute("students", studentDTOs);
        redirectAttributes.addFlashAttribute("msg", "Aluno salvo com sucesso!");

        //redirecionamento para a rota
        return new ModelAndView("redirect:alunos");
    }

    @GetMapping("/{id}")
    public ModelAndView showFormForUpdate(@PathVariable("id") Long id) {

        log.debug("Mostrando o formulário de edição de aluno com lista de alunos");
        ModelAndView mv = new ModelAndView("form-students");

        if(id < 0){
            throw new InvalidParamsException("O identificador não pode ser negativo.");
        }

        Optional<Student> oStudent = studentService.findById(id);

        if(!oStudent.isPresent()){
            throw new EntityNotFoundException("O aluno não foi encontrado pelo id informado.");
        }

        StudentDTO studentDTO = studentMapper.toResponseDto(oStudent.get());
        mv.addObject("dto", studentDTO);
        return mv;
    }

    @GetMapping("/relatorio/genero/masculino")
    public ModelAndView showGenreMResult() {
        ModelAndView mv = new ModelAndView("genre-report");
        List<String> students = new ArrayList<>();
        students.add("João");
        students.add("Gilberto");
        students.add("João Junior");
        mv.addObject("students", students);
        return mv;
    }

    @GetMapping("/relatorio/genero/feminino")
    public ModelAndView showGenreFResult() {
        ModelAndView mv = new ModelAndView("genre-report");
        List<String> students = new ArrayList<>();
        students.add("Maria");
        students.add("Mariana");
        students.add("Maria Júlia");
        students.add("Maria");
        students.add("Mariana");
        students.add("Maria Júlia");
        students.add("Maria");
        students.add("Mariana");
        students.add("Maria Júlia");
        mv.addObject("students", students);
        return mv;
    }

    @GetMapping("/relatorio")
    public ModelAndView showGenreReport() {

        log.debug("Mostrando o relatório de gêneros");
        ModelAndView mv = new ModelAndView("genre-report-container");

        List<TabDTO> tabs = new ArrayList<>();
        tabs.add(new TabDTO("Masculino", "alunos/relatorio/genero/masculino"));
        tabs.add(new TabDTO("Feminino", "alunos/relatorio/genero/feminino"));

        mv.addObject("tabs", tabs);
        return mv;
    }

    @GetMapping("/relatorio-tab-estatico")
    public ModelAndView showPageReport() {

        log.debug("Mostrando o relatório de gêneros");
        ModelAndView mv = new ModelAndView("tabs-container");

        return mv;
    }

    @GetMapping("/relatorio/masculino/paginacao")
    public ModelAndView showGenreMPageResult(HttpServletRequest request,
                                             @RequestParam(value = "pag", defaultValue = "1") int page,
                                             @RequestParam(value = "siz", defaultValue = "3") int size,
                                             @RequestParam(value = "ord", defaultValue = "registration") String order,
                                             @RequestParam(value = "dir", defaultValue = "ASC") String direction){

        ModelAndView mv = new ModelAndView("genre-report");

        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(order).ascending().and(Sort.by("name")));
        //Page<Student> studentPage = studentService.findMasculine(GenderEnum.MASCULINE, pageRequest);
        Page<Student> studentPage = studentService.findAll(pageRequest);
        //lista de alunos
        List<StudentDTO> studentDTOs = studentPage.stream()
                .map(s -> studentMapper.toResponseDto(s))
                .collect(Collectors.toList());
        mv.addObject("students", studentDTOs);

        PaginationDTO paginationDTO = PaginationUtil.getPaginationDTO(studentPage, "/alunos/relatorio/masculino/paginacao");
        mv.addObject("pagination", paginationDTO);
        return mv;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.debug("Removendo um aluno com id {}", id);
        Optional<Student> o = this.studentService.findById(id);

        if (!o.isPresent()) {
            throw new EntityNotFoundException("Erro ao remover, registro não encontrado para o id " + id);
        }

        this.studentService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Aluno removido com sucesso!");
        return "redirect:/alunos";
    }

    @GetMapping("/certificado")
    public ModelAndView showCertificate() {
        ModelAndView mv = new ModelAndView("form-certificate");
        return mv;
    }

    @GetMapping("/download-certificado")
    public ModelAndView downloadCertificate() {
        ModelAndView mv = new ModelAndView("certificate");
        return mv;
    }

    @PostMapping("/certificado")
    public ModelAndView save(@Validated CertificateDTO dto, BindingResult errors, RedirectAttributes redirectAttributes) throws IOException, TranscoderException, ConfigurationException, TransformerException {

        //verifica os erros de validação
        if(errors.hasErrors()){
            ModelAndView mv = new ModelAndView("form-certificate");
            //salva o DTO para manter tais dados em caso de erro
            mv.addObject("dto", dto);
            //salva os erros para serem apresentados
            mv.addObject("errors", errors.getAllErrors());
            //encaminhamento para a visão
            return mv;
        }

        Optional<Student> oStudent = studentService.findByEmail(dto.getEmail());

        if(!oStudent.isPresent()){
            throw new EntityNotFoundException("Não há registros para o email informado!");
        }

        //gera o certificado para o aluno
        File pdfFile = studentService.generateCertificate(svgCertificateTemplate, oStudent.get().getName(), dto.getYear());

        //guarda em nuvem
        Map uploadResult = cloudinary.uploader().upload(pdfFile, ObjectUtils.asMap("folder", "resources"));

        // remove o arquivo temporário na máquina
        pdfFile.delete();

        //envia um email com a URL do certificado
        String content = emailService.getContentMailCertificate(oStudent.get().getName(), (String)uploadResult.get("url"));

        try {
            emailService.sendEmailToClient("Certificado", "ronifabio@gmail.com", content);
            redirectAttributes.addFlashAttribute("email", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //redirecionamento para a rota
        redirectAttributes.addFlashAttribute("certificate", uploadResult.get("url"));
        return new ModelAndView("redirect:/alunos/download-certificado");
    }

}
