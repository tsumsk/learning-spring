import org.springframework.context.support.GenericXmlApplicationContext;

public class AutowiringExampleUsingXmlApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        Target t = null;

        System.out.println("Using byName:\n");
        t = ctx.getBean("targetByName", Target.class);

        System.out.println("\nUsing byType:\n");
        t = ctx.getBean("targetByType", Target.class);

        System.out.println("\nUsing constructor:\n");
        t = ctx.getBean("targetConstructor", Target.class);

        ctx.close();
    }
}
