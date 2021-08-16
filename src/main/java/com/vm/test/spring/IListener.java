package com.vm.test.spring;

import com.vm.test.spi.IDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

import java.util.ServiceLoader;

/**
 * @author huaihai.guan
 * @date 2020/12/9 14:51
 */
public class IListener implements ApplicationListener<ApplicationEvent>, Ordered {

    private static Logger logger = LoggerFactory.getLogger(IListener.class);

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        logger.info(applicationEvent.getClass().getName());
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
            ServiceLoader<IDriver> drivers = ServiceLoader.load(IDriver.class);
            drivers.forEach(IDriver::init);
        }

    }
}
