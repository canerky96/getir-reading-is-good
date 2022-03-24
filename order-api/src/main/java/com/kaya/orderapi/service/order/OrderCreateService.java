package com.kaya.orderapi.service.order;

import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.enums.OrderStatus;
import com.kaya.orderapi.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

  private final OrderWriteService orderWriteService;
  private final SecurityUtils securityUtils;

  @Transactional(rollbackFor = Exception.class)
  public Order create() {
    var order =
        Order.builder().username(securityUtils.getUsername()).status(OrderStatus.CREATED).build();
    return orderWriteService.save(order);
  }
}
