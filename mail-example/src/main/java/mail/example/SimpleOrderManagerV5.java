package mail.example;

import java.util.Arrays;
import java.util.Date;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class SimpleOrderManagerV5 implements OrderManager {

  @Resource(name = "mailSender")
  private JavaMailSender javaMailSender;

  @Autowired
  private TemplateEngine emailTemplateEngine;

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

      Context ctx = new Context();
      ctx.setVariable("name", "liuxu");
      ctx.setVariable("subscriptionDate", new Date());
      ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
      String text = emailTemplateEngine.process("html/email-simple.html", ctx);
      helper.setText(text, true);

      javaMailSender.send(msg);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
