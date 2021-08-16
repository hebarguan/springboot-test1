package com.vm.test.aop;

import com.vm.test.context.RequestContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author huaihai.guan
 * @since 2021/8/12 19:07
 */
@Aspect
@Order(-2)
@Component
public class LogAspect {

    @Around(value = "execution(* com.vm.test.action.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        long start = System.currentTimeMillis();
        Object returnValue = null;
        try {
            returnValue = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("@Aspect URL " + RequestContext.get() + " RT " + (end - start));
        RequestContext.remove();
        return returnValue;
    }

}
