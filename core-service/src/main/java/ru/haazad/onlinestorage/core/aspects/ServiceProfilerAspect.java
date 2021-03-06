package ru.haazad.onlinestorage.core.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.core.utils.ServiceProfilerUtil;

@Component
@RequiredArgsConstructor
@Aspect
public class ServiceProfilerAspect {
    private final ServiceProfilerUtil serviceProfilerUtil;

    @Around("execution(* ru.haazad.onlinestorage.core.services..*.*(..))")
    public Object serviceProfiler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        serviceProfilerUtil.addService(proceedingJoinPoint.getTarget().getClass().getSimpleName(), duration);
        return out;
    }
}
