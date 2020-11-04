import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class ComplexAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar guitar) {
        if (guitar.getBrand().equals("Gibson")) {
            System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName());
        }
    }

    public void simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Guitar guitar) throws Throwable {
        System.out.println("Before Execution: " +
            proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
            proceedingJoinPoint.getSignature().getName() + " " +
            "brand:" + guitar.getBrand());

        Object ret = proceedingJoinPoint.proceed();

        System.out.println("After Execution: " +
            proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
            proceedingJoinPoint.getSignature().getName() + " " +
            "brand: " + guitar.getBrand());
    }
}
