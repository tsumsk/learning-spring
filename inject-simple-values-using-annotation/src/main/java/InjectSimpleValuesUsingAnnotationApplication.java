import inject.simple.values.using.annotation.InjectSimple;
import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectSimpleValuesUsingAnnotationApplication {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        InjectSimple simple = (InjectSimple) ctx.getBean("injectSimple");
        System.out.println(simple);

        ctx.close();
    }
}
