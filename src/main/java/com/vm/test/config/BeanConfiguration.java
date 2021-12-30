package com.vm.test.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vm.test.aop.LogMethodInterceptor;
import com.vm.test.component.TestBean1;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huaihai.guan
 * @since 2021/8/13 15:27
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(new LogMethodInterceptor());

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.vm.test.action.*.*(..))");

        advisor.setPointcut(pointcut);
        advisor.setOrder(-1);
        return advisor;
    }

    @Bean
    public TestBean1 testBean1() {
        TestBean1 testBean1 = new TestBean1();
        testBean1.setName("hebar");
        return testBean1;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

}
