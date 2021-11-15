package com.vm.test.service.bo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huaihai.guan
 * @since 2021/11/8 15:44
 */
public class KeyWordFact {

    private Integer riskScore = 0;

    private Map<String, String> result = new HashMap<>();

    private String userName;

    private String goodsName;

    private String notes;


    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }


}
