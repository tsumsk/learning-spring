import bean.name.aware.example.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

public class BeanNameAwareExample {
    @ComponentScan(basePackages = "bean.name.aware.example")
    private static class Config {
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Singer singer = ctx.getBean("singer", Singer.class);
        singer.sing();

        ctx.close();
    }
}
