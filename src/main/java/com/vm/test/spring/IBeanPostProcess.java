package com.vm.test.spring;

import com.vm.test.component.TestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author huaihai.guan
 * @date 2020/12/15 18:36
 */
@Component
public class IBeanPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof TestBean) {
            TestBean testBean = (TestBean) bean;
            testBean.setName("LastModifyName");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
