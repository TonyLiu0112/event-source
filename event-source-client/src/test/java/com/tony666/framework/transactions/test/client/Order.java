package com.tony666.framework.transactions.test.client;

import com.tony666.framework.transactions.core.Event;
import com.tony666.framework.transactions.core.aggregate.CommandProcessingAggregate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Order extends CommandProcessingAggregate<OrderCommand> {

    @SuppressWarnings("unused")
    public List<Event> process(OrderCreatedCommand orderCommand) {
        return Stream.of(new OrderCreatedEvent(orderCommand.getOrderId(), orderCommand.getMoney())).collect(Collectors.toList());
    }

}
