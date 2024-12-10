package br.com.order.stub;

import br.com.order.enums.OrderStatus;
import br.com.order.model.Order;
import br.com.order.model.Product;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class OrderStub {

    public static Order of() {
        return Order.builder()
                .id(1L)
                .uuid(UUID.randomUUID().toString())
                .status(OrderStatus.SUCCESS)
                .total(BigDecimal.valueOf(100))
                .products(Set.of(Product.builder()
                        .name("Product name")
                        .price(BigDecimal.valueOf(100))
                        .build()))
                .build();
    }
}
