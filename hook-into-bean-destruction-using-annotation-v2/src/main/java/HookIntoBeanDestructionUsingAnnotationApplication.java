import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class HookIntoBeanDestructionUsingAnnotationApplication {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        DestructiveBean bean = ctx.getBean("destructiveBean", DestructiveBean.class);

        System.out.println("calling close()");
        ctx.close();
        System.out.println("called close()");
    }
}
