package formatter.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class FormatterExampleApplication {
  public static void main(String[] args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    Singer john = ctx.getBean("john", Singer.class);
    System.out.println(john);

    ctx.close();
  }

}
