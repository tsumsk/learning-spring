package aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotationAdvice {
    @Pointcut("execution(* sing*(aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example.Guitar)) && args(guitar)")
    public void myPointcut(Guitar guitar) {
    }

    @Pointcut("bean(grammy*)")
    public void isGrammy() {
    }

    @Before("myPointcut(guitar) && isGrammy()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar guitar) {
        if (guitar.getBrand().equals("Gibson")) {
            System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName() + " " +
                joinPoint.getSignature().getName());
        }
    }

    @Around("myPointcut(guitar) && isGrammy()")
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
