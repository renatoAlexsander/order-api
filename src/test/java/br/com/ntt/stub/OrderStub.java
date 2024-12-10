package br.com.ntt.stub;

import br.com.ntt.enums.OrderStatus;
import br.com.ntt.model.Order;
import br.com.ntt.model.Product;

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
