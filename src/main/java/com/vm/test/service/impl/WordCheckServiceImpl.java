package com.vm.test.service.impl;

import com.vm.test.service.WordCheckService;
import com.vm.test.service.bo.KeyWordFact;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huaihai.guan
 * @since 2021/11/8 15:45
 */
@Service
public class WordCheckServiceImpl implements WordCheckService {

    @Resource
    private KieContainer kieContainer;

    @Override
    public KeyWordFact doRulesCheck() {
        // 事实数据
        KeyWordFact keyWordFact = new KeyWordFact();
        keyWordFact.setUserName("万某某");
        keyWordFact.setGoodsName("刷单商品");
        keyWordFact.setNotes("特殊要求真实有效,假一赔十");
        // 执行规则
        KieSession kieSession = kieContainer.newKieSession("word-ks");
        kieSession.insert(keyWordFact);
        kieSession.fireAllRules();
        kieSession.dispose();
        // 决策
        if (keyWordFact.getRiskScore() > 0) {
            // do something
        }
        return keyWordFact;
    }
}
