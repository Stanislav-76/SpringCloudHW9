package ru.gb.timer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.gb.TimerProperties;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TimerAspect {

    private final TimerProperties properties;
    @Pointcut("within(@ru.gb.timer.Timer *)")
    public void beansAnnotatedWith() {

    }

    @Pointcut("@annotation(ru.gb.timer.Timer)")
    public void methodsAnnotatedWith() {

    }
    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object methodExecutionTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.atLevel(properties.getLogLevel()).log("ClassName: " + joinPoint.getSignature().getDeclaringType() + " MethodName: " + joinPoint.getSignature().getName() + " выполнился за " + elapsedTime + " миллисекунд");
        return result;

    }

}
