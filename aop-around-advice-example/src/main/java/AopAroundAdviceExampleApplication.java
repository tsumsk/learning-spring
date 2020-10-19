import org.springframework.aop.framework.ProxyFactory;

public class AopAroundAdviceExampleApplication {
    public static void main(String[] args) {
        WorkerBean target = new WorkerBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new ProfilingInterceptor());
        WorkerBean proxy = (WorkerBean) proxyFactory.getProxy();

        proxy.doSomeWork(10000000);
    }
}
