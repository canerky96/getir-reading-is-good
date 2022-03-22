package com.kaya.orderapi.service;

import com.kaya.orderapi.client.bookapi.dto.BookResponseDTO;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.enums.OrderStatus;
import com.kaya.orderapi.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

  private final OrderWriteService orderWriteService;

  @Transactional(rollbackFor = Exception.class)
  public Order create(List<BookResponseDTO> books) {
    var bookIds = books.stream().map(BookResponseDTO::getId).collect(Collectors.toList());
    return Order.builder()
        .username(SecurityUtils.getUsername())
        .status(OrderStatus.CREATED)
        .books(bookIds)
        .build();
  }
}
