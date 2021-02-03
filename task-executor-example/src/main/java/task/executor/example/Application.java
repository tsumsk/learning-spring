package task.executor.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Application {
  public static void main(String[] args) throws Exception {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    TestTaskExecutorService service = ctx.getBean("testTaskExecutorService",
        TestTaskExecutorService.class);

    service.executeTask();

    System.in.read();

    ctx.close();
  }
}
