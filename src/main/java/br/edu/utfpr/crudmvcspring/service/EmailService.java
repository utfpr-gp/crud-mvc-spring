package br.edu.utfpr.crudmvcspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${support.mail}")
    private String supportMail;

    public void sendEmailToClient(String subject, String email, String content) throws MessagingException {

        // SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mail);
        message.setSubject(subject);
        message.setText(content, true);
        message.setFrom(supportMail);
        message.setTo(email);

        mailSender.send(mail);
    }

    public String getContentMailCertificate(String name, String url){
        return  "<p>Ol&aacute;," + name + "! O seu certificado j&aacute; foi emitido.</p>" +
                "<p>Ele est&aacute; dispon&iacute;vel no seguinte link:</p>" +
                "<p><a href=\"" + url + "\" target=\"_blank\">Ver o meu Certificado</a></p>";
    }
}
