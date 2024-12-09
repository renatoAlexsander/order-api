package br.com.ntt.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(@NotEmpty String name, @NotNull BigDecimal price) {
}
