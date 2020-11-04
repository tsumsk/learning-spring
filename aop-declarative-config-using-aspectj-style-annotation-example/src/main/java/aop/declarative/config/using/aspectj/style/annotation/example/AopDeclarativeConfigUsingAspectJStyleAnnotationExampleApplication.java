package aop.declarative.config.using.aspectj.style.annotation.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class AopDeclarativeConfigUsingAspectJStyleAnnotationExampleApplication {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Documentarist documentarist = ctx.getBean("documentarist", Documentarist.class);

        documentarist.execute();
    }
}
