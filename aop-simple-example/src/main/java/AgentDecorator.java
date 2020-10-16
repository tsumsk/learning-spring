import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AgentDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.print("James ");

        Object ret = invocation.proceed();

        System.out.println("!");

        return ret;
    }
}
