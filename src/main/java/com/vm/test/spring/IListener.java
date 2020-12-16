package com.vm.test.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author huaihai.guan
 * @date 2020/12/9 14:51
 */
@Component
public class IListener implements ApplicationListener<ApplicationEvent>, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

    }
}
