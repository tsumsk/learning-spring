package mail.example;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@ComponentScan(basePackages = "mail.example")
public class AppConfig {

  @Bean
  public MailSender mailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.office365.com");
    mailSender.setPort(587);

    mailSender.setUsername("");
    mailSender.setPassword("");

    Properties properties = mailSender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.debug", "true");

    return mailSender;
  }

  @Bean
  public TemplateEngine emailTemplateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(htmlTemplateResolver());

    return templateEngine;
  }

  private ITemplateResolver htmlTemplateResolver() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
    templateResolver.setPrefix("/mail/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    templateResolver.setCacheable(false);

    return templateResolver;
  }

}
