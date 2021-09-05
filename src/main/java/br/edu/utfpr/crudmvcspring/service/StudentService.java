package br.edu.utfpr.crudmvcspring.service;

import br.edu.utfpr.crudmvcspring.model.entity.GenderEnum;
import br.edu.utfpr.crudmvcspring.model.entity.Student;
import br.edu.utfpr.crudmvcspring.model.repository.StudentRepository;
import br.edu.utfpr.crudmvcspring.util.CPFUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.fop.activity.ContainerUtil;
import org.apache.fop.configuration.Configuration;
import org.apache.fop.configuration.ConfigurationException;
import org.apache.fop.configuration.DefaultConfigurationBuilder;
import org.apache.fop.svg.PDFTranscoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student entity){
        return studentRepository.save(entity);
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }

    public List<Student> findAll(){
        return this.studentRepository.findAll();
    }

    public Page<Student> findAll(PageRequest pageRequest){
        return this.studentRepository.findAll(pageRequest);
    }

    public Page<Student> findMasculine(GenderEnum genderEnum, PageRequest pageRequest){
        return this.studentRepository.findByGender(genderEnum, pageRequest);
    }

    public Page<Student> findAll(Pageable pageRequest){
        return this.studentRepository.findAll(pageRequest);
    }

    public Optional<Student> findById(Long id){
        return this.studentRepository.findById(id);
    }

    public List<Student> findByCourseAndName(String course, String name){
        return this.studentRepository.findByCourseAndName(course, name);
    }

    public List<Student> findByBirthDateBetween(Date d1, Date d2){
        return this.studentRepository.findByBirthDateBetween(d1, d2);
    }

    public List<Student> findByCourseOrderByName(String course){
        return studentRepository.findByCourseOrderByName(course);
    }

    public List<Student> findByGenderIsNotNull(){
        return studentRepository.findByGenderIsNotNull();
    }

    public List<Student> findByNameLike(String name){
        return studentRepository.findByNameLike(name);
    }

    public List<Student> findByCourse(String course){
        return studentRepository.findByCourse(course);
    }

    public Page<Student> findByNameEndsWith(@Param("name") String name, Pageable pageable){
        return studentRepository.findByNameEndsWith(name, pageable);
    }

    public Optional<Student> findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public void init() {
        Student s1 = new Student("João de Sá Pato", "Eng. Mecânica", "joao@email.com", new Date());
        Student s2 = new Student("José de Sá Pato", "Eng. Mecânica", "jose@email.com", new Date());
        Student s3 = new Student("Júlio de Sá Pato", "Eng. Mecânica", "julio@email.com", new Date());

        List<Student> students = Arrays.asList(s1, s2, s3);
        log.debug("Inicializando o BD com {} objetos da classe {}", students.size(), Student.class.toString());
        studentRepository.saveAll(students);
    }

    /**
     * Recebe a URL do template em SVG e gera o certificado em PDF, com as alterações devidas, retornando
     * o File do PDF armazenado no diretório temporário local.
     * @param svgCertificateTemplate
     * @param name
     * @param year
     * @return
     * @throws TranscoderException
     * @throws IOException
     * @throws ConfigurationException
     * @throws TransformerException
     */
    public File generateCertificate(String svgCertificateTemplate, String name, String year) throws TranscoderException, IOException, ConfigurationException, TransformerException {

        //busca o template de certificado em SVG na nuvem
        URL url = new URL(svgCertificateTemplate);

        //realiza a customização
        Document doc = updateCertificate(url.openStream(), name, year);

        //persiste as alterções em um SVG temporário
        File fileSVG = convertDocumentToFile(doc);

        File filePDF = convertSVGToPDF(fileSVG);

        return filePDF;
    }

    /**
     * Recebe o SVG de certificado e realiza as edições de texto no DOM
     * @param svgInputStream
     * @param name
     * @param year
     * @return
     * @throws IOException
     */
    private Document updateCertificate(InputStream svgInputStream, String name, String year) throws IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);

        Document doc = f.createDocument(null, svgInputStream);
        Element nameElement = doc.getElementById("student-name");
        nameElement.setTextContent(name);

        Element yearElement = doc.getElementById("year");
        yearElement.setTextContent(year);

        return doc;
    }

    /**
     * Recebe um document DOM e realiza a transformação em um arquivo persistido temporáriamente
     * @param doc
     * @return
     * @throws IOException
     * @throws TransformerException
     */
    private File convertDocumentToFile(Document doc) throws IOException, TransformerException {
        Path pathSVG = Files.createTempFile("certificado", ".svg");
        File fileSVG = pathSVG.toFile();
        FileWriter fileWriter = new FileWriter(fileSVG);

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(fileWriter);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(source, result);
        return fileSVG;
    }

    /**
     * Converte um SVG para PDF
     * @param fileSVG
     * @return
     * @throws IOException
     * @throws ConfigurationException
     * @throws TranscoderException
     */
    private File convertSVGToPDF(File fileSVG) throws IOException, ConfigurationException, TranscoderException {

        PDFTranscoder transcoder = new PDFTranscoder();

        //carrega o arquivo de configuração
        DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
        Configuration cfg = cfgBuilder.buildFromFile(ResourceUtils.getFile("classpath:fop-config.xml"));
        ContainerUtil.configure(transcoder, cfg);

        //Faz a leitura do novo SVG
        TranscoderInput transcoderInput = new TranscoderInput(new FileInputStream(fileSVG));

        //define o diretório temporário e o prefixo e sufixo do arquivo
        Path pathPDF = Files.createTempFile("certificado", ".pdf");
        File filePDF = pathPDF.toFile();

        //realiza a transformação de SVG para PDF e guarda na pasta temporária
        TranscoderOutput transcoderOutput = new TranscoderOutput(new FileOutputStream(filePDF));
        transcoder.transcode(transcoderInput, transcoderOutput);

        return filePDF;
    }
}
