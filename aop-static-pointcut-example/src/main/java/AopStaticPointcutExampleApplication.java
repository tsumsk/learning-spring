import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AopStaticPointcutExampleApplication {
    public static void main(String[] args) {
        GoodGuitarist goodGuitarist = new GoodGuitarist();
        GreatGuitarist greatGuitarist = new GreatGuitarist();

        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(goodGuitarist);
        proxyFactory.addAdvisor(advisor);
        Singer proxy1 = (Singer) proxyFactory.getProxy();

        proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(greatGuitarist);
        proxyFactory.addAdvisor(advisor);
        Singer proxy2 = (Singer) proxyFactory.getProxy();

        proxy1.sing();
        proxy2.sing();
    }
}
