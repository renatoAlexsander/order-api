package br.com.ntt.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class OrderRequest {

    @NotEmpty
    private String uuid;

    @NotNull @NotEmpty
    private Set<ProductRequest> productsRequest;

}
