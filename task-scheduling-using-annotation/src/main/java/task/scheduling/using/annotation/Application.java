package task.scheduling.using.annotation;

import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Application {
  public static void main(String[] args) throws IOException {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    System.in.read();

    ctx.close();
  }

}
