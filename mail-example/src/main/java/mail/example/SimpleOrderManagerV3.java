package mail.example;

import javax.annotation.Resource;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderManagerV3 implements OrderManager {

  @Resource(name = "mailSender")
  private JavaMailSender javaMailSender;

  @Override
  public void placeOrder(Order order) {
    // business logic

    // send mail
    MimeMessage msg = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(msg);
    try {
      helper.setFrom("");
      helper.setTo("");
      helper.setSubject("SendMailTest");
      helper.setText("liuxu send mail test v3");
      javaMailSender.send(msg);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
