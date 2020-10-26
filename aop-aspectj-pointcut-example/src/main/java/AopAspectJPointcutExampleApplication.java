import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AopAspectJPointcutExampleApplication {
    public static void main(String[] args) {
        GrammyGuitarist grammyGuitarist = new GrammyGuitarist();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* sing*())");

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
