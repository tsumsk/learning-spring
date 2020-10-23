import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> cls) {
        System.out.println("static check for " + method.getName());
        return "foo".equals(method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> cls, Object[] args) {
        System.out.println("dynamic check for " + method.getName());
        int x = ((Integer)args[0]).intValue();
        return x != 100;
    }

    @Override
    public ClassFilter getClassFilter() {
        return cls -> cls == SampleBean.class;
    }
}
