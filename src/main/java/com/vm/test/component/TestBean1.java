package com.vm.test.component;

/**
 * @author huaihai.guan
 * @since 2021/8/2 16:49
 */
public class TestBean1 {

    private String name;

    public TestBean1() {

        System.out.println("TestBean1 Construct");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
