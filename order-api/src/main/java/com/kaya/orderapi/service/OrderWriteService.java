package com.kaya.orderapi.service;

import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderWriteService {

  private final OrderRepository orderRepository;

  @Transactional(rollbackFor = Exception.class)
  public Order save(Order order) {
    return orderRepository.save(order);
  }
}
