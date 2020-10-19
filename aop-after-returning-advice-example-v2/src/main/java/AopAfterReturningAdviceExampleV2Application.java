import org.springframework.aop.framework.ProxyFactory;

public class AopAfterReturningAdviceExampleV2Application {
    public static void main(String[] args) {
        KeyGenerator target = new KeyGenerator();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new WeakKeyCheckAdvice());
        KeyGenerator proxy = (KeyGenerator)proxyFactory.getProxy();

        for (int x = 0; x < 10; x++) {
            try {
                long key = proxy.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException ex) {
                System.out.println("weak key generated");
            }
        }
    }
}
