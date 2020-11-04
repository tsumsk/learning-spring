package aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopDeclarativeConfigUsingAspectjStyleAnnotationAndSpringBootExampleApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(
            AopDeclarativeConfigUsingAspectjStyleAnnotationAndSpringBootExampleApplication.class, args);

        Documentarist documentarist = ctx.getBean("documentarist", Documentarist.class);

        documentarist.execute();
    }
}
