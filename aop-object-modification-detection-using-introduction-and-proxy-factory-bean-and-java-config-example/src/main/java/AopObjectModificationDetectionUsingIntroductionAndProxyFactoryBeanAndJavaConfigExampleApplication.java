import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopObjectModificationDetectionUsingIntroductionAndProxyFactoryBeanAndJavaConfigExampleApplication {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Contact proxy = ctx.getBean("proxy", Contact.class);
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is Contact?: " + (proxy instanceof Contact));
        System.out.println("Is IsModified?: " + (proxy instanceof IsModified));

        System.out.println("Has been modified?: " + proxyInterface.isModified());

        proxy.setName("John Mayer");

        System.out.println("Has been modified?: " + proxyInterface.isModified());

        proxy.setName("Eric Clapton");

        System.out.println("Has been modified?: " + proxyInterface.isModified());
    }
}
