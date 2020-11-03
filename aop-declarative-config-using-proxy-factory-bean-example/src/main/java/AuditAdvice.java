import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class AuditAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("Executing: " + method);
    }

}
