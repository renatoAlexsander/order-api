package br.com.order.model;

import br.com.order.enums.OrderStatus;
import br.com.order.stub.OrderRequestStub;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldCreateOrderStatusSuccessAndCalculateTotal() {
        var orderRequest = OrderRequestStub.of();
        var result = Order.of(orderRequest);

        assertEquals(OrderStatus.SUCCESS, result.getStatus());
        assertEquals(BigDecimal.valueOf(100), result.getTotal());
    }
}