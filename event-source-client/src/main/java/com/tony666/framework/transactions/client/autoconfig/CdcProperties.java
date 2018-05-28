package com.tony666.framework.transactions.client.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.consul.cdc", value = "cdcService")
public class CdcProperties {

    private String serverName;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
