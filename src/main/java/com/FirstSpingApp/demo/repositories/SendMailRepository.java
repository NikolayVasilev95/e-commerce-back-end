package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.SendMail;
import com.FirstSpingApp.demo.resources.UserIdProjection;
import com.FirstSpingApp.demo.util.enums.MailType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SendMailRepository extends JpaRepository<SendMail, Long> {

  List<SendMail> findByExpireDateBefore(Date expireDate);

  List<UserIdProjection> findByExpireDateGreaterThanEqualAndSendMailType(
      Date today, MailType mailType);
}
