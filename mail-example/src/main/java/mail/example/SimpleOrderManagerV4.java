package mail.example;

import java.io.File;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderManagerV4 implements OrderManager {

  @Resource(name = "mailSender")
  private JavaMailSender javaMailSender;

  @Override
  public void placeOrder(Order order) {
    // business logic

    // send mail
    MimeMessage msg = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(msg, true);
      helper.setFrom("");
      helper.setTo("");
      helper.setSubject("SendMailTest");

      helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
      ClassPathResource resource = new ClassPathResource("xibu.jpg");
      helper.addInline("identifier1234", resource);

      javaMailSender.send(msg);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
