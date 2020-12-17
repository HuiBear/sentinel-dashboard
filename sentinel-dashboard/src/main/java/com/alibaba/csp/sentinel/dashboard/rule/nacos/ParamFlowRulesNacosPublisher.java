package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.config.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ParamFlowRulesNacosPublisher
 * @Description TODO
 * @Author xiongh
 * @Date 2020/12/10 14:43
 * @Version 1.0
 **/
@Component("paramFlowRulesNacosPublisher")
public class ParamFlowRulesNacosPublisher implements DynamicRulePublisher<List<ParamFlowRule>> {

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<List<ParamFlowRule>, String> converter;

    @Override
    public void publish(String app, List<ParamFlowRule> rules) throws Exception {
        configService.publishConfig(app + NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID,
                converter.convert(rules));
    }
}
