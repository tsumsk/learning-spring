import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import prototype.beans.using.annotation.Singer;

public class PrototypeBeansUsingAnnotationApplication {
    @ComponentScan(basePackages = "prototype.beans.using.annotation")
    public static class Config {
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Singer singer1 = ctx.getBean("nonSingleton", Singer.class);
        Singer singer2 = ctx.getBean("nonSingleton", Singer.class);

        System.out.println(singer1 == singer2);
        System.out.println(singer1.equals(singer2));
        System.out.println(singer1);
        System.out.println(singer2);

        ctx.close();
    }
}
