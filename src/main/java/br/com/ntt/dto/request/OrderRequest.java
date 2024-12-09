package br.com.ntt.dto.request;

import java.util.Set;

public record OrderRequest(String uuid,
                           Set<ProductRequest> productsRequest) {
}
