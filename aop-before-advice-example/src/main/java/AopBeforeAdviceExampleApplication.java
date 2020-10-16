import org.springframework.aop.framework.ProxyFactory;

public class AopBeforeAdviceExampleApplication {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing();
    }
}
