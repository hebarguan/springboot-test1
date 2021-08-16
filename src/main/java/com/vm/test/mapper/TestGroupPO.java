package com.vm.test.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author huaihai.guan
 * @since 2021/8/2 18:11
 */
@TableName("test_group_table")
public class TestGroupPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("channelid")
    private Integer channelId;

    @TableField("subid")
    private Integer subId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }
}
