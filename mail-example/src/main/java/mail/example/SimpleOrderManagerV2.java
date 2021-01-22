package mail.example;

import javax.annotation.Resource;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderManagerV2 implements OrderManager {

  @Resource(name = "mailSender")
  private JavaMailSender javaMailSender;

  @Override
  public void placeOrder(Order order) {
    // business logic

    // send mail
    MimeMessagePreparator preparator = mimeMessage -> {
      mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(""));
      mimeMessage.setFrom("");
      mimeMessage.setSubject("SendMailTest");
      mimeMessage.setText("liuxu send mail test v2");
    };

    try {
      javaMailSender.send(preparator);
    } catch (MailException e) {
      System.err.println(e.getMessage());
    }
  }
}
