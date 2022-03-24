package com.kaya.orderapi.service.statistic;

import com.kaya.orderapi.dto.request.OrderStatisticsQueryParamDTO;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.OrderedBook;
import com.kaya.orderapi.service.order.OrderService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

  @InjectMocks private StatisticsService statisticsService;
  @Mock private OrderService orderService;

  private Order order;

  @SneakyThrows
  @Before
  public void init() {
    // Create order
    Date createDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
    order = new Order();
    order.setUsername("username");
    order.setId(1L);
    order.setCreateDate(createDate);

    // Create ordered books
    var orderedBook1 = new OrderedBook();
    orderedBook1.setOrder(order);
    orderedBook1.setId(1L);
    orderedBook1.setPrice(123L);
    var orderedBook2 = new OrderedBook();
    orderedBook2.setOrder(order);
    orderedBook2.setId(2L);
    orderedBook2.setPrice(123L);
    // Set ordered books to order
    order.setBooks(List.of(orderedBook1, orderedBook2));
  }

  @Test
  public void shouldSearch() {
    var username = "username";
    var query = OrderStatisticsQueryParamDTO.builder().username(username).build();

    when(orderService.searchByUsername(anyString())).thenReturn(List.of(order));

    var result = statisticsService.search(query);
    verify(orderService).searchByUsername(query.getUsername());
    assertEquals("December", result.get(0).getMonth());
    assertEquals(246L, result.get(0).getTotalPurchasedAmount());
    assertEquals(2, result.get(0).getTotalBookCount());
    assertEquals(1, result.get(0).getTotalOrderCount());
  }
}
