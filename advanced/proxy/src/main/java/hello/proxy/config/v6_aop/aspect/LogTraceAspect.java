package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Aspect
@Slf4j
public class LogTraceAspect {
    private final LogTrace trace;

    public LogTraceAspect(LogTrace logTrace) {
        this.trace = logTrace;
    }

    @Around("execution(* hello.proxy.app..*(..))")
    private Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        TraceStatus status = null;
        String message = joinPoint.getSignature().toShortString();
        try{
            status = trace.begin(message);
            //로직 호출
            Object result = joinPoint.proceed();
            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }
    }
}
