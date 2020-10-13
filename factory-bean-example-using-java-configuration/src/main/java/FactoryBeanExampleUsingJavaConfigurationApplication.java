import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FactoryBeanExampleUsingJavaConfigurationApplication {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(MessageDigesterConfig.class);

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");

        ctx.close();
    }
}
