package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.dashboard.config.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName GatewayFlowRulesNacosPublisher
 * @Description TODO
 * @Author xiongh
 * @Date 2020/11/17 21:20
 * @Version 1.0
 **/
@Component("gatewayFlowRulesNacosPublisher")
public class GatewayFlowRulesNacosPublisher implements DynamicRulePublisher<List<GatewayFlowRule>> {

    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<List<GatewayFlowRule>, String> converter;

    @Override
    public void publish(String app, List<GatewayFlowRule> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + NacosConfigUtil.FLOW_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, converter.convert(rules));
    }
}
