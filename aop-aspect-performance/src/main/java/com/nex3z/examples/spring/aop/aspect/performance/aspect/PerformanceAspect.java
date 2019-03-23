package com.nex3z.examples.spring.aop.aspect.performance.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect @Component
@Slf4j
public class PerformanceAspect {

    @Pointcut("execution(* com.nex3z.spring.examples.aop.aspect.performance.repository..*(..))")
    private void repositoryOps() { }

    // @Around("execution(* com.nex3z.spring.examples.aop.aspect.performance.repository..*(..))")
    @Around("repositoryOps()")
    public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "success";
        try {
            name = pjp.getSignature().toShortString();
            return pjp.proceed();
        } catch (Throwable t) {
            result = "error";
            throw t;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("logPerformance(): name = {}, result = {}, elapsed = {}", name, result, endTime - startTime);
        }
    }

}
