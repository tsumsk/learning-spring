import org.springframework.context.support.GenericXmlApplicationContext;

public class AopDeclarativeConfigUsingAopExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context-03.xml");
        ctx.refresh();

        Documentarist documentarist = ctx.getBean("documentarist", Documentarist.class);

        documentarist.execute();
    }
}
