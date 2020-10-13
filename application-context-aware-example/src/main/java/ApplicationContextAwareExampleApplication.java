import application.context.aware.example.DestructiveBean;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ApplicationContextAwareExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        DestructiveBean bean = ctx.getBean("destructiveBean", DestructiveBean.class);
    }
}
