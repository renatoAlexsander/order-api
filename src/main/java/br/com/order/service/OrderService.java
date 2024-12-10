package br.com.order.service;

import br.com.order.dto.request.OrderRequest;
import br.com.order.dto.response.OrderResponse;
import br.com.order.model.Order;
import br.com.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<OrderResponse> findAll(Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        return orderRepository.findAll(pageRequest)
                .map(OrderResponse::of);
    }
}
