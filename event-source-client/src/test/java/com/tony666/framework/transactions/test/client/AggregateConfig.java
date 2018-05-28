package com.tony666.framework.transactions.test.client;

import com.tony666.framework.transactions.client.RestCrud;
import com.tony666.framework.transactions.client.RestAggregateRepository;
import com.tony666.framework.transactions.client.integration.CdcIntegration;
import com.tony666.framework.transactions.core.AggregateRepository;
import com.tony666.framework.transactions.core.Crud;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AggregateConfig {

    @Bean
    public Crud crud(CdcIntegration cdcIntegration) {
        return new RestCrud(cdcIntegration);
    }

    @Bean
    public AggregateRepository<Order, OrderCommand> orderAggregateRepository(Crud crud) {
        //noinspection unchecked
        return new RestAggregateRepository(Order.class, crud);
    }

}
