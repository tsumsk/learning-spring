import mail.example.AppConfig;
import mail.example.Order;
import mail.example.OrderManager;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class MailExampleTest {
  @Test
  public void testSendMail() {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderManager orderManager = ctx.getBean("simpleOrderManager", OrderManager.class);
    orderManager.placeOrder(new Order());
    ctx.close();
  }

  @Test
  public void testSendMailV2() {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderManager orderManager = ctx.getBean("simpleOrderManagerV2", OrderManager.class);
    orderManager.placeOrder(new Order());
    ctx.close();
  }

  @Test
  public void testSendMailV3() {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderManager orderManager = ctx.getBean("simpleOrderManagerV3", OrderManager.class);
    orderManager.placeOrder(new Order());
    ctx.close();
  }

  @Test
  public void testSendMailV4() {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderManager orderManager = ctx.getBean("simpleOrderManagerV4", OrderManager.class);
    orderManager.placeOrder(new Order());
    ctx.close();
  }

  @Test
  public void testSendMailV5() {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderManager orderManager = ctx.getBean("simpleOrderManagerV5", OrderManager.class);
    orderManager.placeOrder(new Order());
    ctx.close();
  }
}
