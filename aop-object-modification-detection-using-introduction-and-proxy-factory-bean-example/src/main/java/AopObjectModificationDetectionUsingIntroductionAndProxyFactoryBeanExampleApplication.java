import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopObjectModificationDetectionUsingIntroductionAndProxyFactoryBeanExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

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
