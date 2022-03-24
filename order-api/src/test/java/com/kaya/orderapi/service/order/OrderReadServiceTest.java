package com.kaya.orderapi.service.order;

import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.repository.OrderRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderReadServiceTest {

  @InjectMocks private OrderReadService orderReadService;
  @Mock private OrderRepository orderRepository;
  @Mock private JPAQueryFactory query;

  @Test
  public void shouldFindById() {
    var id = 1L;
    var order = new Order();

    when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

    var result = orderReadService.findById(id);

    verify(orderRepository).findById(id);
    assertEquals(order, result);
  }

  @Test(expected = OrderApiException.class)
  public void shouldNotFindById() {
    var id = 1L;

    when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

    orderReadService.findById(id);
    verify(orderRepository).findById(id);
  }

  @Test
  public void shouldFindPageableByUsername() {
    var username = "username";
    var pageable = PageRequest.of(0, 1);
    var orders = List.of(new Order());

    when(orderRepository.findAllByUsername(anyString(), any()))
        .thenReturn(new PageImpl<>(orders, pageable, orders.size()));

    var result = orderReadService.findPageableByUsername(username, pageable);

    verify(orderRepository).findAllByUsername(username, pageable);
    assertEquals(orders, result.getContent());
  }

  @Test(expected = OrderApiException.class)
  public void shouldNotFindPageableByUsername() {
    var username = "username";
    var pageable = PageRequest.of(0, 1);
    List<Order> orders = new ArrayList<>();

    when(orderRepository.findAllByUsername(anyString(), any()))
        .thenReturn(new PageImpl<>(orders, pageable, 0));

    var result = orderReadService.findPageableByUsername(username, pageable);

    verify(orderRepository).findAllByUsername(username, pageable);
  }

  @Test
  public void shouldFindAllByUsername() {
    var username = "username";
    var orders = List.of(new Order());

    when(orderRepository.findAllByUsername(anyString())).thenReturn(orders);

    var result = orderReadService.findAllByUsername(username);

    verify(orderRepository).findAllByUsername(username);
    assertEquals(orders, result);
  }
}
