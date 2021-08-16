package com.vm.test.action;

import com.vm.test.service.TestGroupService;
import com.vm.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huaihai.guan
 * @date 2020/12/17 18:39
 */
@RestController
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestService testService;

    @Resource
    private TestGroupService testGroupService;

    @GetMapping(value = "/my/test")
    public String test() {
        testGroupService.doTest();
        testService.doTest();
        return "ok";
    }
}
