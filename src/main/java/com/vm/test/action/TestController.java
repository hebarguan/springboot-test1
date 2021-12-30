package com.vm.test.action;

import com.alibaba.fastjson.JSONObject;
import com.vm.test.component.ParamBean;
import com.vm.test.service.TestGroupService;
import com.vm.test.service.TestService;
import com.vm.test.service.WordCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private WordCheckService wordCheckService;

    @Resource
    private TestGroupService testGroupService;

    @GetMapping(value = "/my/test")
    public String test() {
//        testGroupService.doTest();
        testService.doTest();
        return "ok";
    }

    @PostMapping(value = "/my/test1")
    public String test1(@Validated @RequestBody ParamBean paramBean) {
        return paramBean.getName();
    }

    @GetMapping(value = "/my/test2")
    public String test2() {
        return JSONObject.toJSONString(wordCheckService.doRulesCheck());
    }
}
