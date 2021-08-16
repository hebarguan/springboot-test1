package com.vm.test.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author huaihai.guan
 * @since 2021/8/2 17:59
 */
@TableName("mytest")
public class TestPO {

    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    @TableField("score")
    private Integer score;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
