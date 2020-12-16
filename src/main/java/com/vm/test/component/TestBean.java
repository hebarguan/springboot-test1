package com.vm.test.component;

import com.vm.test.annotation.TestComponent;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author huaihai.guan
 * @date 2020/12/9 17:37
 */
@TestComponent
public class TestBean implements InitializingBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    private void init() {
        this.name = "hebarguan";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.name = "myname";
    }
}
