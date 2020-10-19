import org.springframework.aop.framework.ProxyFactory;

public class AopAfterReturningAdviceExampleApplication {
    public static void main(String[] args) {
        Guitarist target = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new SimpleAfterReturningAdvice());
        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing();
    }
}
