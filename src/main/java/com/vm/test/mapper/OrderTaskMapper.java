package com.vm.test.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author huaihai.guan
 * @since 2021/11/26 15:02
 */
@Repository
@DS("main")
public interface OrderTaskMapper extends BaseMapper<OrderTask> {
}
