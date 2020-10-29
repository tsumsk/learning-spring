import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class AopComposablePointcutExampleApplication {
    public static void main(String[] args) {
        GrammyGuitarist target = new GrammyGuitarist();

        ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        System.out.println("Test 1");
        GrammyGuitarist proxy = getProxy(pointcut, target);
        testInvoke(proxy);

        System.out.println("Test 2");
        pointcut.union(new TalkMethodMatcher());
        proxy = getProxy(pointcut, target);
        testInvoke(proxy);

        System.out.println("Test 3");
        pointcut.intersection(new RestMethodMatcher());
        proxy = getProxy(pointcut, target);
        testInvoke(proxy);

    }

    private static GrammyGuitarist getProxy(ComposablePointcut pointcut, GrammyGuitarist target) {
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        return (GrammyGuitarist) proxyFactory.getProxy();
    }

    private static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(100);
        proxy.talk();
        proxy.rest();
    }

    private static class SingMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> cls) {
            return method.getName().startsWith("si");
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> cls) {
            return method.getName().equals("talk");
        }
    }

    private static class RestMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> cls) {
            return method.getName().endsWith("st");
        }
    }
}
