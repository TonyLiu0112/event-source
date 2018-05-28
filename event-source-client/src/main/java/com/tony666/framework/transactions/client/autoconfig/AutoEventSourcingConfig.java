package com.tony666.framework.transactions.client.autoconfig;

import com.tony666.framework.transactions.client.RestCrud;
import com.tony666.framework.transactions.client.integration.CdcIntegration;
import com.tony666.framework.transactions.core.Crud;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CdcProperties.class)
@ComponentScan(basePackages = "com.tony666.framework.transactions.client")
@EnableFeignClients(basePackages = "com.tony666.framework.transactions.client.integration")
public class AutoEventSourcingConfig {

    @Bean
    public Crud crud(CdcIntegration cdcIntegration) {
        return new RestCrud(cdcIntegration);
    }

}
