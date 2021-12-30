package com.vm.test.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author huaihai.guan
 * @date 2021/11/26 15:20
 */
@Repository
@DS("holo")
public interface HoloMapper extends BaseMapper<HoloWsfHelper> {
}
