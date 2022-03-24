package com.kaya.orderapi.service.customer;

import com.kaya.orderapi.dto.request.OrderQueryParamDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.mapper.OrderMapper;
import com.kaya.orderapi.service.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @InjectMocks private CustomerService customerService;
  @Mock private OrderService orderService;
  @Mock private OrderMapper orderMapper;

  @Test
  public void shouldSearch() {
    var query = OrderQueryParamDTO.builder().page(0).pageSize(10).username("username").build();
    var page = PageRequest.of(query.getPage(), query.getPageSize());
    var orders = List.of(new Order());
    var orderResponse = new OrderResponseDTO();
    var argumentCaptor = ArgumentCaptor.forClass(PageRequest.class);

    when(orderService.searchByUsername(anyString(), any(PageRequest.class)))
        .thenReturn(new PageImpl<>(orders, page, orders.size()));
    when(orderMapper.mapToResponse(any(Order.class))).thenReturn(orderResponse);

    var result = customerService.search(query);

    verify(orderService).searchByUsername(eq(query.getUsername()), argumentCaptor.capture());
    assertEquals(orderResponse, result.getContent().get(0));
  }
}
