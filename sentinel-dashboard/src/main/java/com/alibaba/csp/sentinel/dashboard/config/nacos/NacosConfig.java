package com.alibaba.csp.sentinel.dashboard.config.nacos;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @ClassName NacosConfig
 * @Description TODO
 * @Author xiongh
 * @Date 2020/11/14 15:38
 * @Version 1.0
 **/
@Configuration
public class NacosConfig {

    @Value("${nacos.server.address:localhost}")
    private String nacosServerAddress;

    @Bean
    public Converter<List<FlowRule>, String> flowRuleEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRule>> flowRuleDecoder() {
        return s -> JSON.parseArray(s, FlowRule.class);
    }

    @Bean
    public Converter<List<DegradeRule>, String> degradeRuleEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<DegradeRule>> degradeRuleDecoder() {
        return s -> JSON.parseArray(s, DegradeRule.class);
    }

    @Bean
    public Converter<List<GatewayFlowRule>, String> gatewayFlowRuleEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<GatewayFlowRule>> gatewayFlowRuleDecoder() {
        return s -> JSON.parseArray(s, GatewayFlowRule.class);
    }

    @Bean
    public Converter<String, List<ParamFlowRule>> paramFlowRuleDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRule.class);
    }

    @Bean
    public Converter<List<ParamFlowRule>, String> paramFlowRuleEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<AuthorityRule>> authorityRuleDecoder() {
        return s -> JSON.parseArray(s, AuthorityRule.class);
    }

    @Bean
    public Converter<List<AuthorityRule>, String> authorityRuleEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<List<SystemRule>, String> systemRuleEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<SystemRule>> systemRuleDecoder() {
        return s -> JSON.parseArray(s, SystemRule.class);
    }

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        return ConfigFactory.createConfigService(this.nacosServerAddress);
    }
}
