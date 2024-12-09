package br.com.ntt.service;

import br.com.ntt.dto.request.OrderRequest;
import br.com.ntt.dto.response.OrderResponse;
import br.com.ntt.model.Order;
import br.com.ntt.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void save(OrderRequest orderRequest) {
        var order = Order.of(orderRequest);
        log.info("Saving order: {}", order);
        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public OrderResponse findBy(String uuid) {
        return orderRepository.findByUuid(uuid)
                .map(OrderResponse::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }
}
