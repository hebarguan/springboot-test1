package com.vm.test.injected;

import com.vm.test.spring.IInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author huaihai.guan
 * @since 2021/12/8 16:42
 */
@Component
public class TestInjection implements BeanNameAware {

    @IInstantiationAwareBeanPostProcessor.TestAnnotation
    private IInstantiationAwareBeanPostProcessor.Test test;


    @PostConstruct
    private void init() {

        System.out.println("injected success: " + test.getName());
    }

    @Override
    public void setBeanName(String beanName) {

        System.out.println("BeanNameAware: " + beanName);
    }
}
