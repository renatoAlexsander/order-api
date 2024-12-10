package br.com.ntt.stub;

import br.com.ntt.dto.request.ProductRequest;

import java.math.BigDecimal;

public class ProductRequestStub {

    public static ProductRequest of(String name) {
        return ProductRequest.builder()
                .name(name)
                .price(BigDecimal.valueOf(50))
                .build();
    }
}
