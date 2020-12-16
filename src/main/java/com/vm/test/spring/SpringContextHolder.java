package com.vm.test.spring;

import com.vm.test.component.TestBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author huaihai.guan
 * @date 2020/12/9 18:02
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext  = applicationContext;
        System.out.println(applicationContext.getBean(TestBean.class).getName());

        AbstractEnvironment  abstractEnvironment = (AbstractEnvironment) applicationContext.getEnvironment();

        abstractEnvironment.getPropertySources().forEach(new Consumer<PropertySource<?>>() {
            @Override
            public void accept(PropertySource<?> propertySource) {

                System.out.println(propertySource.getName());
            }
        });
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
