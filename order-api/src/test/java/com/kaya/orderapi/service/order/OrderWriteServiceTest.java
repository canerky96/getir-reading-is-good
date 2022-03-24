package com.kaya.orderapi.service.order;

import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderWriteServiceTest {

  @InjectMocks private OrderWriteService orderWriteService;

  @Mock private OrderRepository orderRepository;

  @Test
  public void shouldSave() {
    var order = new Order();
    when(orderRepository.save(order)).thenReturn(order);
    var result = orderWriteService.save(order);
    verify(orderRepository).save(order);
    assertEquals(order, result);
  }
}
