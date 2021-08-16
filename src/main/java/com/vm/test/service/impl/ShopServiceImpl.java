package com.vm.test.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.vm.test.mapper.ShopMapper;
import com.vm.test.mapper.ShopPO;
import com.vm.test.service.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author huaihai.guan
 * @since 2021/8/11 18:36
 */
@Service
@DS("other")
@Transactional(propagation = Propagation.SUPPORTS)
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Override
    public void doUpdate() {
        ShopPO shopPO = new ShopPO();
        shopPO.setId(1001);
        shopPO.setSumNum(8);
        int e = shopMapper.updateById(shopPO);
        System.out.println(e);
//        throw new RuntimeException("no updated");
    }
}
