package com.vm.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vm.test.mapper.ShopMapper;
import com.vm.test.mapper.ShopPO;
import com.vm.test.mapper.TestGroupMapper;
import com.vm.test.mapper.TestGroupPO;
import com.vm.test.service.ShopService;
import com.vm.test.service.TestGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author huaihai.guan
 * @since 2021/8/2 18:17
 */
@Service
@DS("holo")
@Transactional
public class TestGroupServiceImpl implements TestGroupService {

    @Resource
    private ShopService shopService;

    @Resource
    private TestGroupMapper testGroupMapper;

    @Override
    public void doTest() {
        TestGroupPO testGroupPO = testGroupMapper.selectById(1);
        System.out.println(testGroupPO.getChannelId());
        testGroupPO.setChannelId(18);
        testGroupMapper.updateById(testGroupPO);
//        try {
//            shopService.doUpdate();
//        } catch (RuntimeException var1) {
//
//            System.out.println(var1.getMessage());
//        }
    }

}
