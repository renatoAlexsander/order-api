package br.com.ntt.consumer;

import br.com.ntt.dto.request.OrderRequest;
import br.com.ntt.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @RetryableTopic(attempts = "2", backoff = @Backoff(delay = 1_500, multiplier = 2))
    @KafkaListener(topics = "${kafka.topics.orders}", groupId = "orders-group")
    public void consumeOrder(String message) {
        try {
            log.info("Consuming message: {}", message);
            var orderRequest = objectMapper.readValue(message, OrderRequest.class);
            log.info("Message parsed: {}", orderRequest);

            orderService.save(orderRequest);
        } catch (DataIntegrityViolationException ex) {
            log.warn("Record already exists and will be ignored {}", message);
            throw ex;
        } catch (JsonProcessingException ex) {
            log.error("Message will be ignored {}", message);
        } catch (Exception ex) {
            log.error("Error to save order {}", message);
            throw ex;
        }
    }
}
