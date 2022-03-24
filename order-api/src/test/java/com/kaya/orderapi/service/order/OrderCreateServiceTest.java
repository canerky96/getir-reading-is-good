package com.kaya.orderapi.service.order;

import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.enums.OrderStatus;
import com.kaya.orderapi.utils.SecurityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderCreateServiceTest {

  @InjectMocks private OrderCreateService orderCreateService;
  @Mock private OrderWriteService orderWriteService;
  @Mock private SecurityUtils securityUtils;

  @Test
  public void shouldCreate() {
    var username = "username";
    var argumentCaptor = ArgumentCaptor.forClass(Order.class);
    var order = new Order();

    when(orderWriteService.save(any(Order.class))).thenReturn(order);
    when(securityUtils.getUsername()).thenReturn(username);

    var result = orderCreateService.create();

    verify(securityUtils).getUsername();
    verify(orderWriteService).save(argumentCaptor.capture());
    assertEquals(username, argumentCaptor.getValue().getUsername());
    assertEquals(OrderStatus.CREATED, argumentCaptor.getValue().getStatus());
    assertEquals(order, result);
  }
}
