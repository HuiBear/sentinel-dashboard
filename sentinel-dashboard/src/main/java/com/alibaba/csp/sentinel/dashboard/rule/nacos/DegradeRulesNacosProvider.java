package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.config.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DegradeRulesNacosProvider
 * @Description TODO
 * @Author xiongh
 * @Date 2020/11/24 14:56
 * @Version 1.0
 **/
@Component("degradeRulesNacosProvider")
public class DegradeRulesNacosProvider implements DynamicRuleProvider<List<DegradeRule>> {

    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<String, List<DegradeRule>> converter;

    @Override
    public List<DegradeRule> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, 3000);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }
}
