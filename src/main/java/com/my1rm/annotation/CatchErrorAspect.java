package com.my1rm.annotation;

import com.my1rm.api.API;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CatchErrorAspect {

    @Around("@annotation(CatchError)")
    public Object handle(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try{
            return proceedingJoinPoint.proceed();
        }catch (Exception e){
            API.catchException(e);
            return null;
        }
    }
}
