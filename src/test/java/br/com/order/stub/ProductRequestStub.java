package br.com.order.stub;

import br.com.order.dto.request.ProductRequest;

import java.math.BigDecimal;

public class ProductRequestStub {

    public static ProductRequest of(String name) {
        return ProductRequest.builder()
                .name(name)
                .price(BigDecimal.valueOf(50))
                .build();
    }
}
