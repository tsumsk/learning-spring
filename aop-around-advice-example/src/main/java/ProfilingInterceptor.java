import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();

        sw.start(invocation.getMethod().getName());

        Object returnVal = invocation.proceed();

        sw.stop();

        dumpInfo(invocation, sw.getTotalTimeMillis());

        return returnVal;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        Method m = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();

        System.out.println("Execute method: " + m.getName());
        System.out.println("On object of type: " + target.getClass().getName());
        System.out.println("With arguments: ");
        for (int x = 0; x < args.length; x++) {
            System.out.print("\t> " + args[x]);
        }
        System.out.println();

        System.out.println("Took: " + ms + " ms");
    }
}
