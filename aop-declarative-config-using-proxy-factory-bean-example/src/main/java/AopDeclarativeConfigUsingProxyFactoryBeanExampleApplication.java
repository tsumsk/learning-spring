import org.springframework.context.support.GenericXmlApplicationContext;

public class AopDeclarativeConfigUsingProxyFactoryBeanExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        documentaristOne.execute();

        documentaristTwo.execute();
    }
}
