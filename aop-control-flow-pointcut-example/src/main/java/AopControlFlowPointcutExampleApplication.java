import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AopControlFlowPointcutExampleApplication {
    public static void main(String[] args) {
        TestBean target = new TestBean();

        Pointcut pointcut = new ControlFlowPointcut(AopControlFlowPointcutExampleApplication.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        TestBean proxy = (TestBean) proxyFactory.getProxy();

        System.out.println("Trying normal invoke");
        proxy.foo();

        System.out.println("Trying under test()");
        test(proxy);
    }

    private static void test(TestBean bean) {
        bean.foo();
    }
}
