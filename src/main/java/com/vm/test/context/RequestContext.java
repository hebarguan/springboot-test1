package com.vm.test.context;

import org.springframework.core.NamedThreadLocal;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author huaihai.guan
 * @since 2021/8/12 14:22
 */
public class RequestContext {

    private static ThreadLocal<String> REQUEST_HOLDER = new ThreadLocal<>();

    private static ThreadLocal<LinkedBlockingDeque<String>> REQUEST_NEW_HOLDER = new NamedThreadLocal<>("block");

    public static void set(String param) {
        REQUEST_HOLDER.set(param);
    }

    public static String get() {
        return REQUEST_HOLDER.get();
    }

    public static void remove() {
        REQUEST_HOLDER.remove();
    }
}
