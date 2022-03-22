package com.kaya.orderapi.service.customer;

import com.kaya.orderapi.dto.request.OrderQueryParamDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.mapper.OrderMapper;
import com.kaya.orderapi.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  public Page<OrderResponseDTO> search(OrderQueryParamDTO query) {
    var pageRequest = PageRequest.of(query.getPage(), query.getPageSize());
    var orders = orderService.searchByUsername(query.getUsername(), pageRequest);
    var response =
        orders.getContent().stream().map(orderMapper::mapToResponse).collect(Collectors.toList());
    return new PageImpl<>(response, pageRequest, orders.getTotalElements());
  }
}
