package com.vm.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vm.test.mapper.TestMapper;
import com.vm.test.mapper.TestPO;
import com.vm.test.service.TestService;
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

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void doTest() {
        TestPO testPO = testMapper.selectById(1);
        System.out.println(testPO.getScore());
    }

}
