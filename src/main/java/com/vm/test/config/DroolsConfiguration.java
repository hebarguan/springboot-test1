package com.vm.test.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author huaihai.guan
 * @since 2021/11/8 9:10
 */
@Configuration
public class DroolsConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().newKieClasspathContainer();
    }

    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase(KieContainer container) {
        System.out.println("get drools kieBase");
        return container.getKieBase();
    }

}
