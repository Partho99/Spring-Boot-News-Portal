package com.etcenteprise.newsoftheearth.services;

import com.etcenteprise.newsoftheearth.entities.MailRequestAndResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration templateConfiguration;

    public MailRequestAndResponse sendEmail(MailRequestAndResponse request, Map<String, Object> model) {
        MailRequestAndResponse response = new MailRequestAndResponse();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            //helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            Template template = templateConfiguration.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(request.getTo());
            helper.setText(html, true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            javaMailSender.send(message);
            response.setResponseMessage("mail send to : " + request.getTo());
            response.setResponseStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setResponseMessage("Mail Sending failure : " + e.getMessage());
            response.setResponseStatus(Boolean.FALSE);
            e.printStackTrace();
        }
        return response;
    }
}
