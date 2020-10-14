import org.springframework.context.support.GenericXmlApplicationContext;

public class FactoryBeanAndFactoryMethodExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");

        ctx.close();
    }
}
