import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
  public static void main(String[] args) throws IOException {
    ApplicationContext ctx =
        new ClassPathXmlApplicationContext("app-context.xml");

    System.in.read();
  }
}
