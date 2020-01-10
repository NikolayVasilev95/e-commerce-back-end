package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.SendMail;
import com.FirstSpingApp.demo.repositories.SendMailRepository;
import com.FirstSpingApp.demo.resources.UserIdProjection;
import com.FirstSpingApp.demo.services.SendMailService;
import com.FirstSpingApp.demo.util.enums.MailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SendMailServiceImpl implements SendMailService {

  @Autowired private SendMailRepository sendMailRepository;

  @Override
  public SendMail create(SendMail sendMail) {
    return sendMailRepository.save(sendMail);
  }

  @Override
  public List<SendMail> getSendMailsWhiteExpireDate(Date expireDate) {
    return sendMailRepository.findByExpireDateBefore(expireDate);
  }

  @Override
  public int updateAllSendMailsWhiteExpireDate(List<SendMail> sendMailList) {
    sendMailList.forEach(sendMail -> sendMail.setExpireDate(null));
    sendMailRepository.saveAll(sendMailList);
    return sendMailList.size();
  }

  @Override
  public List<UserIdProjection> getSentMailsToUsers(Date date, MailType mailType) {
    return sendMailRepository.findByExpireDateGreaterThanEqualAndSendMailType(date, mailType);
  }
}
