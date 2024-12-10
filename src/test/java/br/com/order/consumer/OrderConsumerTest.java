package br.com.order.consumer;

import br.com.order.dto.request.OrderRequest;
import br.com.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderConsumerTest {

    @InjectMocks
    private OrderConsumer orderConsumer;

    @Mock
    private OrderService orderService;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    void shouldConsumeOrder() {
        String message = """
                {
                  "uuid": "123e4567-e89b-12d3-a456-426614174000",
                  "productsRequest": [
                    {
                      "name": "Product 1",
                      "price": 29.99
                    },
                    {
                      "name": "Product 2",
                      "price": 49.99
                    }
                  ]
                }
                """;
        orderConsumer.consumeOrder(message);

        verify(orderService).save(any(OrderRequest.class));
    }

    @Test
    void shouldNotSaveOrderWhenAlreadyExists() {
        String message = """
                {
                  "uuid": "123e4567-e89b-12d3-a456-426614174000",
                  "productsRequest": [
                    {
                      "name": "Product 1",
                      "price": 29.99
                    },
                    {
                      "name": "Product 2",
                      "price": 49.99
                    }
                  ]
                }
                """;

        doThrow(new DataIntegrityViolationException("Registro duplicado!"))
                .when(orderService).save(any(OrderRequest.class));

        assertDoesNotThrow(() -> orderConsumer.consumeOrder(message));
    }

    @Test
    void shouldThrowExceptionWhenOrderServiceReturnError() {
        String message = """
                {
                  "uuid": "123e4567-e89b-12d3-a456-426614174000",
                  "productsRequest": [
                    {
                      "name": "Product 1",
                      "price": 29.99
                    },
                    {
                      "name": "Product 2",
                      "price": 49.99
                    }
                  ]
                }
                """;

        doThrow(new RuntimeException("Erro desconhecido"))
                .when(orderService).save(any(OrderRequest.class));

        assertThrows(Exception.class, () -> orderConsumer.consumeOrder(message));
    }

    @Test
    void shouldNotConsumeOrderWhenMessageIsInvalid() {
        String message = """
                {
                  "uuidsssssss": "123e4567-e89b-12d3-a456-426614174000",
                  "teste": [
                    {
                      "name": "Product 1",
                      "price": 29.99
                    },
                    {
                      "name": "Product 2",
                      "price": 49.99
                    }
                  ]
                }
                """;

        assertDoesNotThrow(() -> orderConsumer.consumeOrder(message));

        verify(orderService, never()).save(any(OrderRequest.class));
    }
}