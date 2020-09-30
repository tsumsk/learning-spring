import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class BeanNamingUsingXmlApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
// for app-context.xml
//        ctx.load("app-context.xml");
//        ctx.refresh();
//
//        Map<String, String> beans = ctx.getBeansOfType(String.class);
//
//        beans.entrySet().stream().forEach(e -> {
//            System.out.printf("key: " + e.getKey() + " value: " + e.getValue() + "\n");
//        });
//        ctx.close();

// for app-context-02.xml
//        ctx.load("app-context-02.xml");
//        ctx.refresh();
//
//        String s1 = ctx.getBean("john", String.class);
//        String s2 = ctx.getBean("johnny", String.class);
//        String s3 = ctx.getBean("jonathan", String.class);
//        String s4 = ctx.getBean("jim", String.class);
//        String s5 = ctx.getBean("ion", String.class);
//        String s6 = ctx.getBean("jon", String.class);
//
//        System.out.println(s1 == s2);
//        System.out.println(s2 == s3);
//        System.out.println(s3 == s4);
//        System.out.println(s4 == s5);
//        System.out.println(s5 == s6);
//
//        Map<String, String> beans = ctx.getBeansOfType(String.class);
//        if (beans.size() == 1) {
//            System.out.println("There is only one String bean.");
//        }
//
//        ctx.close();

        ctx.load("app-context-03.xml");
        ctx.refresh();

        Map<String, String> beans = ctx.getBeansOfType(String.class);
        beans.entrySet().stream().forEach(e -> {
            System.out.println("id:" + e.getKey() + "\n\talias: " + Arrays.toString(ctx.getAliases(e.getKey())));
        });

        ctx.close();
    }
}
