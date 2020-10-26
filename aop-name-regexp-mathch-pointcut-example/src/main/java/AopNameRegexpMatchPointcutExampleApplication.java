import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class AopNameRegexpMatchPointcutExampleApplication {
    public static void main(String[] args) {
        GrammyGuitarist grammyGuitarist = new GrammyGuitarist();

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*sing.*");

        Advice advice = new SimpleAdvice();

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(grammyGuitarist);
        proxyFactory.addAdvisor(advisor);
        GrammyGuitarist proxy = (GrammyGuitarist) proxyFactory.getProxy();

        proxy.sing();
        proxy.sing2();
        proxy.rest();
        proxy.talk();
    }
}
