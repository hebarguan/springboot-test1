package com.vm.test.spring;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 类名以两个大写字母开始的默认beanName为类名beanName举例 例如：ITestClass 那BeanName 为 ITestClass
 * 以一个大写字母开始的默认BeanName举例：例如: TestClass 那BeanName 为 testClass
 * @author huaihai.guan
 * @since 2021/11/19 9:46
 */

@Component
public class IMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition rootBeanDefinition, Class<?> beanClass, String beanName) {


    }

    @Override
    public void resetBeanDefinition(String beanName) {

    }
}
