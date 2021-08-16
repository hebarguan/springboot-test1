package com.vm.test.aop;

import com.vm.test.context.RequestContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author huaihai.guan
 * @since 2021/8/13 15:32
 */
public class LogMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = null;
        try {
            returnValue = methodInvocation.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("MethodInterceptor URL " + RequestContext.get() + " RT " + (end - start));
        RequestContext.remove();
        return returnValue;
    }
}
