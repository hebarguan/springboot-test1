package com.vm.test.spi;

/**
 * @author huaihai.guan
 * @since 2021/8/11 10:36
 */
public class IDPDriver implements IDriver {

    @Override
    public void init() {

        System.out.println("startUp IDPDriver");
    }
}
