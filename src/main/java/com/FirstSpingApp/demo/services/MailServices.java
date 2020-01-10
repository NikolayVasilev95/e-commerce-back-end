package com.FirstSpingApp.demo.services;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public interface MailServices {

  void createMail(String to, String subject, Object emailModel)
      throws IOException, TemplateException, MessagingException;

  void sendMail(MimeMessage message);
}
