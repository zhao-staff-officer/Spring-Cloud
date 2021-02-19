package com.cloud.staff.apifirst.controller.java.Spring.Asprct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    @Pointcut("@annotation(com.cloud.staff.apifirst.controller.java.Spring.Asprct.TestAnnotation)")
    public void execCutPoint(){}

    @Around("execCutPoint() && @annotation(testAnnotation)")
    public Object aroundAnnation(ProceedingJoinPoint joinPoint, TestAnnotation testAnnotation) throws Throwable {
        System.out.println(testAnnotation.name());
        return  joinPoint.proceed();
    }
}
