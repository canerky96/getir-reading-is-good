package com.kaya.orderapi.service.order;

import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.exception.ErrorCode;
import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderReadService {

  private final OrderRepository orderRepository;

  @Transactional(readOnly = true)
  public Order findById(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new OrderApiException(ErrorCode.ORDER_NOT_FOUND));
  }
  @Transactional(readOnly = true)
  public Page<Order> findPageableByUsername(String username, Pageable pageable) {
    var orders = orderRepository.findAllByUsername(username, pageable);
    if (CollectionUtils.isEmpty(orders.getContent())) {
      throw new OrderApiException(ErrorCode.ORDERS_NOT_FOUND_WITH_USERNAME);
    }
    return orders;
  }
}
