package com.FirstSpingApp.demo.services.scheduled;

import com.FirstSpingApp.demo.domain.SendMail;
import com.FirstSpingApp.demo.domain.ShoppingCart;
import com.FirstSpingApp.demo.resources.EmailModel;
import com.FirstSpingApp.demo.resources.UserIdProjection;
import com.FirstSpingApp.demo.services.MailServices;
import com.FirstSpingApp.demo.services.SendMailService;
import com.FirstSpingApp.demo.services.ShoppingCartService;
import com.FirstSpingApp.demo.services.UserService;
import com.FirstSpingApp.demo.util.DateUtil;
import com.FirstSpingApp.demo.util.constant.MailMassage;
import com.FirstSpingApp.demo.util.enums.MailType;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledTasksServices {

  @Autowired private ShoppingCartService shoppingCartService;
  @Autowired private UserService userService;
  @Autowired private MailServices mailServices;
  @Autowired private SendMailService sendMailService;

  /** Scheduled task is executed at 00:00 AM every day */
  @Scheduled(cron = "0 0 0 ? * *")
  //    @Scheduled(fixedRate = 20000)
  @Async("threadPoolTaskExecutor")
  public void scheduleCleanShoppingCartTask() {
    Date today = new Date();
    List<SendMail> sendMailList = sendMailService.getSendMailsWhiteExpireDate(today);
    List<Long> userIdList =
        sendMailList.stream().map(SendMail::getUserId).collect(Collectors.toList());
    int result = shoppingCartService.emptyShoppingCarts(userIdList);
    sendMailService.updateAllSendMailsWhiteExpireDate(sendMailList);
    System.out.println(
        "Thread: "
            + Thread.currentThread().getName()
            + "- Clean shopping cart task - "
            + System.currentTimeMillis() / 1000);
    System.out.println("Carts emptied: " + result);
  }

  /** Scheduled task is executed at 00:00 AM every day */
  @Scheduled(cron = "0 30 0 ? * *")
  //  @Scheduled(cron = "*/10 * * * * ?")
  @Async("threadPoolTaskExecutor")
  public void scheduleUncompletedOrderTask() {
    Date sendDate = new Date();
    List<Long> userIdList =
        sendMailService.getSentMailsToUsers(sendDate, MailType.UNCOMPLETED_ORDER).stream()
            .map(UserIdProjection::getUserId)
            .collect(Collectors.toList());
    List<ShoppingCart> shoppingCarts = shoppingCartService.getFullShoppingCarts(userIdList);
    shoppingCarts.forEach(
        shoppingCart -> {
          String mail = shoppingCart.getUser().getEmail();
          EmailModel model =
              new EmailModel(
                  shoppingCart.getUser().getFirstName(),
                  shoppingCart.getUser().getLastName(),
                  MailMassage.UNCOMPLETED_ORDER);
          try {
            mailServices.createMail(mail, MailMassage.SUBJECT_UNCOMPLETED_ORDER, model);
            SendMail sendMail =
                new SendMail(
                    shoppingCart.getUser().getId(),
                    sendDate,
                    DateUtil.addDays(sendDate, 3),
                    MailType.UNCOMPLETED_ORDER);
            sendMailService.create(sendMail);
          } catch (IOException | TemplateException | MessagingException e) {
            e.printStackTrace();
          }
          System.out.println(
              "Thread: "
                  + Thread.currentThread().getName()
                  + " - Uncompleted order task - "
                  + LocalDateTime.now());
        });
  }
}
