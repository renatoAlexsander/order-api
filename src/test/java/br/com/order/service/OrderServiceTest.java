package br.com.order.service;

import br.com.order.model.Order;
import br.com.order.repository.OrderRepository;
import br.com.order.stub.OrderRequestStub;
import br.com.order.stub.OrderStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldSaveOrder() {
        orderService.save(OrderRequestStub.of());

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void shouldFindAllOrders() {
        var orders = new PageImpl<>(List.of(OrderStub.of()));

        when(orderRepository.findAll(any(Pageable.class)))
                .thenReturn(orders);

        var result = orderService.findAll(0, 10);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }
}