package com.tony666.framework.transactions.test.client;

import com.tony666.framework.transactions.core.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderCreatedEvent implements Event {

    private String orderId;

    private BigDecimal money;
}
