package com.tony666.framework.transactions.test.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderCreatedCommand implements OrderCommand {

    private String orderId;

    private BigDecimal money;

}
