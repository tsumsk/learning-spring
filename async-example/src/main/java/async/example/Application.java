package async.example;

import java.io.IOException;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Application {
  private static Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);

    for (int i = 0; i < 5; i++) {
      asyncService.asyncTask();
    }

    Future<String> result1 = asyncService.asyncWithReturn("A");
    Future<String> result2 = asyncService.asyncWithReturn("B");
    Future<String> result3 = asyncService.asyncWithReturn("C");

    logger.info("result1: " + result1.get());
    logger.info("result2: " + result2.get());
    logger.info("result3: " + result3.get());

    System.in.read();

    ctx.close();
  }

}
