import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AopDynamicPointcutExampleApplication {
    public static void main(String[] args) {
        SampleBean sampleBean = new SampleBean();

        Pointcut pointcut = new SimpleDynamicPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(sampleBean);
        proxyFactory.addAdvisor(advisor);
        SampleBean proxy = (SampleBean) proxyFactory.getProxy();

        proxy.foo(1);
        proxy.foo(10);
        proxy.foo(100);

        proxy.bar();
        proxy.bar();
        proxy.bar();
    }
}
