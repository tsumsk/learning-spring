import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import spring.http.invoker.example.SingerService;
import spring.http.invoker.example.config.RmiClientConfig;

@ContextConfiguration(classes = RmiClientConfig.class)
@RunWith(SpringRunner.class)
public class SpringHttpInvokerExampleTest {
  @Autowired
  private SingerService singerService;

}
