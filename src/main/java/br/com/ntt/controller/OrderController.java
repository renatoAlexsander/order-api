package br.com.ntt.controller;

import br.com.ntt.dto.response.OrderResponse;
import br.com.ntt.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderResponse> findBy(@PathVariable String uuid) {
        return ResponseEntity.ok(orderService.findBy(uuid));
    }
}
