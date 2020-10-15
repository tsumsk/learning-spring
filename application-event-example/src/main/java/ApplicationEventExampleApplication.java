import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationEventExampleApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        Publisher publisher = ctx.getBean("publisher", Publisher.class);
        publisher.publish("I send an SOS to the world...");
        publisher.publish("... I hope that someone gets my...");
        publisher.publish("... Message in a bottle");
    }
}
