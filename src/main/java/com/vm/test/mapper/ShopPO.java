package com.vm.test.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author huaihai.guan
 * @since 2021/8/11 18:13
 */
@TableName("test.shop_order")
public class ShopPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("sum_num")
    private Integer sumNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }
}
