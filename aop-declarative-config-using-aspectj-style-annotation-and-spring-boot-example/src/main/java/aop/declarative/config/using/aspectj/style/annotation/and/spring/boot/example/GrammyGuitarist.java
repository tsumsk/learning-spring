package aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example;

import org.springframework.stereotype.Component;

@Component
public class GrammyGuitarist {
    public void sing() {
        System.out.println("aop.declarative.config.using.aspectj.style.annotation.example.aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example.GrammyGuitarist sing() called");
    }

    public void sing(Guitar guitar) {
        System.out.printf("aop.declarative.config.using.aspectj.style.annotation.example.aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example.GrammyGuitarist sing(aop.declarative.config.using.aspectj.style.annotation.example.aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example.Guitar) called, brand={%s}\n", guitar.getBrand());
    }

    public void rest() {
        System.out.println("aop.declarative.config.using.aspectj.style.annotation.example.aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example.GrammyGuitarist rest() called");
    }

    public void talk() {
        System.out.println("aop.declarative.config.using.aspectj.style.annotation.example.aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example.GrammyGuitarist talk() called");
    }
}
