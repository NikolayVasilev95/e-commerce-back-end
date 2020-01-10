package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.services.MailServices;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class MailServicesImpl implements MailServices {

  @Autowired private JavaMailSender mailSender;

  @Autowired private Configuration freemarkerConfig;

  @Override
  @Async("EmailThreadPool")
  public void createMail(String to, String subject, Object emailModel)
      throws IOException, TemplateException, MessagingException {

    System.out.println(
        "[ " + Thread.currentThread().getName() + " - " + LocalDateTime.now() + " ]");
    freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
    Template template = freemarkerConfig.getTemplate("email-template.ftl");
    String freeMarkerTemplateText =
        FreeMarkerTemplateUtils.processTemplateIntoString(template, emailModel);

    MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage());

    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(freeMarkerTemplateText, true);
    sendMail(helper.getMimeMessage());
  }

  @Override
  public void sendMail(MimeMessage message) {
    mailSender.send(message);
  }
}
