package com.vm.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vm.test.component.TestBean;
import com.vm.test.mapper.TestMapper;
import com.vm.test.mapper.TestPO;
import com.vm.test.service.TestService;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.cdi.KBase;
import org.kie.api.cdi.KContainer;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author huaihai.guan
 * @since 2021/8/2 18:17
 */
@Service
@DS("main")
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Resource
    private KieContainer kieContainer;


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void doTest() {
        TestPO testPO = testMapper.selectById(1);
        System.out.println(testPO.getScore());
    }

    @Override
    public void doDroolsTest() {
        KieSession kieSession = kieContainer.newKieSession("word-ks");
        TestBean factBean = new TestBean();
        factBean.setName("hebarguan");
        kieSession.insert(factBean);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
