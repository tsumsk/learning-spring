package jsr.bean.validation.example;

import java.net.URL;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.joda.time.DateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JSR349BeanValidationExampleApplication {
  public static void main(String[] args) throws Exception {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    Singer singer = new Singer();
    singer.setFirstName("John");
    singer.setLastName("Mayer");
    singer.setBirthDate(new DateTime());
    singer.setPersonalSite(new URL("ftp://"));

    SingerValidationService singerValidationService = ctx.getBean("singerValidationService",
        SingerValidationService.class);

    Set<ConstraintViolation<Singer>> errors = singerValidationService.validateSinger(singer);

    System.out.println(errors);

    ctx.close();
  }

}
