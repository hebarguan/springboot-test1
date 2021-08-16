package com.vm.test.spring;

import com.vm.test.component.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author huaihai.guan
 * @date 2020/12/15 18:36
 */
@Component
public class IBeanPostProcess implements BeanPostProcessor, InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(IBeanPostProcess.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof TestBean) {
            logger.info("postProcessBeforeInitialization");
            TestBean testBean = (TestBean) bean;
            testBean.setName("LastModifyName");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            logger.info("postProcessAfterInitialization");
        }
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("afterPropertiesSet");
    }

}
