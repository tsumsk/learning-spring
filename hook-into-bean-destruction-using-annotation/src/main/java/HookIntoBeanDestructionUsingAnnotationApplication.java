import org.springframework.context.support.GenericXmlApplicationContext;

public class HookIntoBeanDestructionUsingAnnotationApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        DestructiveBean bean = ctx.getBean("destructiveBean", DestructiveBean.class);

        System.out.println("calling close()");
        ctx.close();
        System.out.println("called close()");
    }
}
