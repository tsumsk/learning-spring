package mail.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderManager implements OrderManager {
  @Autowired
  private MailSender mailSender;

  @Override
  public void placeOrder(Order order) {
    // business logic

    // send mail
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom("");
    msg.setTo("");
    msg.setSubject("SendMailTest");
    msg.setText("liuxu send mail test");
    try {
      mailSender.send(msg);
    } catch (MailException e) {
      System.err.println(e.getMessage());
    }
  }
}
