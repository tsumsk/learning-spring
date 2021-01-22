import distributed.transaction.example.AppConfig;
import distributed.transaction.example.SingerService;
import distributed.transaction.example.entities.Singer;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class DistributedTransactionExampleTest {
  @Test
  public void testSave() {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    SingerService singerService = ctx.getBean("singerService", SingerService.class);
    Singer singer = new Singer();
    singer.setFirstName("kexin");
    singer.setLastName("liu");
    singer.setBirthDate(
        new Date(new GregorianCalendar(2021, 1, 20).getTime().getTime()));

    singerService.save(singer);
    ctx.close();
  }
}
