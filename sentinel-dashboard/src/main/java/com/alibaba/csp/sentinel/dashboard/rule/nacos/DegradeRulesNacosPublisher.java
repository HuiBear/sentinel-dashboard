package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.config.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName DegradeRulesNacosPublisher
 * @Description TODO
 * @Author xiongh
 * @Date 2020/11/24 15:06
 * @Version 1.0
 **/
@Component("degradeRulesNacosPublisher")
public class DegradeRulesNacosPublisher implements DynamicRulePublisher<List<DegradeRule>> {

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<List<DegradeRule>,String> converter;

    @Override
    public void publish(String app, List<DegradeRule> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, converter.convert(rules));
    }
}
