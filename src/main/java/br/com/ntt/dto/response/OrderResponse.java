package br.com.ntt.dto.response;

import br.com.ntt.enums.OrderStatus;
import br.com.ntt.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record OrderResponse(String uuid,
                            OrderStatus status,
                            Set<ProductResponse> products,
                            LocalDateTime createdAt,
                            BigDecimal total) {

    public static OrderResponse of(Order order) {
        return new OrderResponse(
                order.getUuid(),
                order.getStatus(),
                ProductResponse.of(order.getProducts()),
                order.getCreatedAt(),
                order.getTotal()
        );
    }
}
