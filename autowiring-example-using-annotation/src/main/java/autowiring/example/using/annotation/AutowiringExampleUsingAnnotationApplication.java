package autowiring.example.using.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

public class AutowiringExampleUsingAnnotationApplication {
    @ComponentScan(basePackages = "autowiring.example.using.annotation")
    private static class AppConfig {
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Target t = ctx.getBean(Target.class);

        ctx.close();
    }
}
