package br.com.ntt.stub;

import br.com.ntt.dto.request.OrderRequest;

import java.util.Set;
import java.util.UUID;

public class OrderRequestStub {

    public static OrderRequest of() {
        return OrderRequest.builder()
                .uuid(UUID.randomUUID().toString())
                .productsRequest(Set.of(
                        ProductRequestStub.of("Product name 1"),
                        ProductRequestStub.of("Product name 2")
                ))
                .build();
    }
}
