package com.vm.test.spring;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
/**
 * @author huaihai.guan
 * @date 2020/12/4 14:57
 */
public class IApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("自定义上下文初始化");
//        configurableApplicationContext.addBeanFactoryPostProcessor(new IApplicationContextInitializer.IBeanDefinitionRegistryPostProcessor(configurableApplicationContext.getEnvironment()));
    }

    @Override
    public int getOrder() {
        return 0;
    }


}
