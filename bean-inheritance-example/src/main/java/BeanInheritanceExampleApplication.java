import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanInheritanceExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        Singer parent = ctx.getBean("parent", Singer.class);
        Singer child = ctx.getBean("child", Singer.class);

        System.out.println("Parent:\n" + parent);
        System.out.println("Child:\n" + child);
    }
}
