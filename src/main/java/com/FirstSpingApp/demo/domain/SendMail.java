package com.FirstSpingApp.demo.domain;

import com.FirstSpingApp.demo.util.enums.MailType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "send_mail")
public class SendMail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "send_mail_id_generator")
  @SequenceGenerator(
      name = "send_mail_id_generator",
      sequenceName = "send_mail_id_seq",
      allocationSize = 1)
  private Long id;

  @NotNull
  @Column(name = "user_id")
  private Long userId;

  @NotNull
  @Column(name = "send_date")
  @Temporal(TemporalType.DATE)
  private Date sendDate;

  @Column(name = "expire_date")
  @Temporal(TemporalType.DATE)
  private Date expireDate;

  @NotNull
  @Column(name = "send_mail_type")
  @Enumerated(EnumType.STRING)
  private MailType sendMailType;

  public SendMail() {}

  public SendMail(
      @NotNull Long userId,
      @NotNull Date sendDate,
      @NotNull Date expireDate,
      @NotNull MailType sendMailType) {
    this.userId = userId;
    this.sendDate = sendDate;
    this.expireDate = expireDate;
    this.sendMailType = sendMailType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public MailType getSendMailType() {
    return sendMailType;
  }

  public void setSendMailType(MailType sendMailType) {
    this.sendMailType = sendMailType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SendMail)) return false;
    SendMail sendMail = (SendMail) o;
    return Objects.equals(id, sendMail.id)
        && Objects.equals(userId, sendMail.userId)
        && Objects.equals(sendDate, sendMail.sendDate)
        && Objects.equals(expireDate, sendMail.expireDate)
        && Objects.equals(sendMailType, sendMail.sendMailType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, sendDate, expireDate, sendMailType);
  }
}
