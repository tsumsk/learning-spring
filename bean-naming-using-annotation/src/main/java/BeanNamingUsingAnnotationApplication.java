import bean.naming.using.annotation.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class BeanNamingUsingAnnotationApplication {
    @Configuration
    public static class AliasBeanConfig {
        @Bean(name = {"johnMayer", "john", "jonathan", "johnny"})
        public Singer singer() {
            return new Singer();
        }
    }

    public static void main(String[] args) {
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("app-context.xml");
//        ctx.refresh();

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AliasBeanConfig.class);

        Map<String, Singer> beans= ctx.getBeansOfType(Singer.class);

        beans.entrySet().stream().forEach(e ->
            System.out.println("id: " + e.getKey() + "\n\talias: " + Arrays.toString(ctx.getAliases(e.getKey()))));

        ctx.close();
    }
}
