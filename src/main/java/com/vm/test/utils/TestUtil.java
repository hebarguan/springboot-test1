package com.vm.test.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author huaihai.guan
 * @since 2021/11/10 14:32
 */
public class TestUtil {

    public static void main(String[] args) {
        String path = "d/app/rule/demo/file.drl";
        String pr = "rule/";
        System.out.println(StringUtils.substring(path, path.indexOf(pr), path.length()));
    }

}
