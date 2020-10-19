import org.springframework.aop.framework.ProxyFactory;

public class AopThrowsAdviceExampleApplication {
    public static void main(String[] args) {
        ErrorBean target = new ErrorBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new SimpleThrowsAdvice());
        ErrorBean proxy = (ErrorBean)proxyFactory.getProxy();

        try {
            proxy.errorProneMethod();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            proxy.otherErrorProneMethod();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
