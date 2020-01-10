package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.SendMail;
import com.FirstSpingApp.demo.resources.UserIdProjection;
import com.FirstSpingApp.demo.util.enums.MailType;

import java.util.Date;
import java.util.List;

public interface SendMailService {

  SendMail create(SendMail sendMail);

  List<SendMail> getSendMailsWhiteExpireDate(Date expireDate);

  int updateAllSendMailsWhiteExpireDate(List<SendMail> sendMailList);

  List<UserIdProjection> getSentMailsToUsers(Date date, MailType mailType);
}
