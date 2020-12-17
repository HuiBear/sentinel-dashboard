package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.config.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ParamFlowRulesNacosProvider
 * @Description TODO
 * @Author xiongh
 * @Date 2020/12/10 14:43
 * @Version 1.0
 **/
@Component("paramFlowRulesNacosProvider")
public class ParamFlowRulesNacosProvider implements DynamicRuleProvider<List<ParamFlowRule>> {

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String, List<ParamFlowRule>> converter;

    @Override
    public List<ParamFlowRule> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, 3000);
        return converter.convert(rules);
    }
}
