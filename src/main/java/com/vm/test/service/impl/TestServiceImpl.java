package com.vm.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vm.test.mapper.*;
import com.vm.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huaihai.guan
 * @since 2021/8/2 18:17
 */
@Service
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    private Integer updateTotal = 0;

    private Integer page = 5;

    @Resource
    private HoloMapper holoMapper;

    @Resource
    private OrderTaskMapper orderTaskMapper;

    @Override
    public void doTest() {

        System.out.println("doTest");
    }

    private void doNotRunThisMethod() {
        QueryWrapper<OrderTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<OrderTask> iPage;
        do {
            page++;
            iPage = new Page<>(page, 100);
            IPage<OrderTask> orderTask = orderTaskMapper.selectPage(iPage, queryWrapper);
            orderTask.getRecords().forEach(x -> {
                logger.info("orderTask{}", JSONObject.toJSON(x));
                HoloWsfHelper holoWsfHelper = new HoloWsfHelper();
                holoWsfHelper.setPkId("order_task_" + x.getId());
                holoWsfHelper.setOrderTaskOrderId(x.getOrderId());
                int effectedRows = holoMapper.updateById(holoWsfHelper);
                logger.info("update row {} with pk_id {}", effectedRows, holoWsfHelper.getPkId());
                updateTotal += effectedRows;
            });
        } while (iPage.getRecords().size() >= 100);

        System.out.println("finished total page: " + page + ", updateTotal Rows: " + updateTotal);
    }

    @Override
    public void doDroolsTest() {

    }

}
