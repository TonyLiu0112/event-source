package com.tony666.framework.transactions.test.client;

import com.tony666.framework.transactions.core.AggregateRepository;
import com.tony666.framework.transactions.core.repository.response.SaveUpdateRes;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
class OrderService {

    private final AggregateRepository<Order, OrderCommand> orderAggregateRepository;

    public OrderService(AggregateRepository<Order, OrderCommand> orderAggregateRepository) {
        this.orderAggregateRepository = orderAggregateRepository;
    }

    public void createOrder() {
        Optional<SaveUpdateRes> save = orderAggregateRepository.save(new OrderCreatedCommand("101", new BigDecimal(2000)));
        save.ifPresent(System.out::print);
    }

}
