package br.com.ntt.controller;

import br.com.ntt.enums.OrderStatus;
import br.com.ntt.service.OrderService;
import br.com.ntt.stub.OrderResponseStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    void shouldReturnOrders() throws Exception {
        var page = new PageImpl<>(List.of(OrderResponseStub.of()));

        when(orderService.findAll(0, 10))
                .thenReturn(page);

        mockMvc.perform(get("/v1/orders")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfElements").value(1))
                .andExpect(jsonPath("$.content[0].uuid").isString())
                .andExpect(jsonPath("$.content[0].total").value(BigDecimal.valueOf(100)))
                .andExpect(jsonPath("$.content[0].status").value(OrderStatus.SUCCESS.name()))
                .andExpect(jsonPath("$.content[0].products[0].name").value("product name"))
                .andExpect(jsonPath("$.content[0].products[0].price").value(BigDecimal.valueOf(100)))
                .andExpect(jsonPath("$.content[0].products[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.content[0].createdAt").isNotEmpty());
    }

}