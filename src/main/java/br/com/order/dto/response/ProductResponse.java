package br.com.order.dto.response;

import br.com.order.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductResponse(String name, BigDecimal price, LocalDateTime createdAt) {

    public static Set<ProductResponse> of(Set<Product> products) {
        return products.stream()
                .map(product -> new ProductResponse(
                        product.getName(),
                        product.getPrice(),
                        product.getCreatedAt()
                ))
                .collect(Collectors.toSet());
    }
}
