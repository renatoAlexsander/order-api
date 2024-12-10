package br.com.ntt.stub;

import br.com.ntt.dto.response.OrderResponse;
import br.com.ntt.dto.response.ProductResponse;
import br.com.ntt.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class OrderResponseStub {

    public static OrderResponse of() {
        var productResponse = new ProductResponse(
                "product name",
                BigDecimal.valueOf(100),
                LocalDateTime.now()
        );

        return new OrderResponse(
                UUID.randomUUID().toString(),
                OrderStatus.SUCCESS,
                Set.of(productResponse),
                LocalDateTime.now(),
                BigDecimal.valueOf(100)
        );
    }
}
