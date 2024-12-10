package br.com.ntt.model;

import br.com.ntt.enums.OrderStatus;
import br.com.ntt.stub.OrderRequestStub;
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