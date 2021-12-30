package com.vm.test.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author huaihai.guan
 * @since 2021/11/26 15:21
 */
@TableName("t_wshifu_helper")
public class HoloWsfHelper {

    @TableId(value = "pk_id")
    private String pkId;

    private String orderTaskOrderId;

    public String getOrderTaskOrderId() {
        return orderTaskOrderId;
    }

    public void setOrderTaskOrderId(String orderTaskOrderId) {
        this.orderTaskOrderId = orderTaskOrderId;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

}
