import org.springframework.context.support.GenericXmlApplicationContext;

public class PrototypeBeansUsingXmlApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        Singer singer1 = ctx.getBean("nonSingleton", Singer.class);
        Singer singer2 = ctx.getBean("nonSingleton", Singer.class);

        System.out.println(singer1 == singer2);
        System.out.println(singer1.equals(singer2));
        System.out.println(singer1);
        System.out.println(singer2);

        ctx.close();
    }
}
