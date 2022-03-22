package com.kaya.orderapi.controller;

import com.kaya.orderapi.dto.SuccessResponse;
import com.kaya.orderapi.dto.request.OrderCreateRequestDTO;
import com.kaya.orderapi.dto.response.OrderCreateResponseDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(OrderController.ENDPOINT)
@RequiredArgsConstructor
public class OrderController {

  public static final String ENDPOINT = "order";

  private final OrderService orderService;

  //@GetMapping("{id}")
  //@PreAuthorize("hasAuthority('read_order')")
  //public SuccessResponse<OrderResponseDTO> get(@PathVariable("id") Long id) {
  //  var order = orderService.get(id);
  //  return new SuccessResponse<>(order, HttpStatus.OK.value());
  //}

  @PostMapping
  @PreAuthorize("hasAuthority('create_order')")
  public SuccessResponse<OrderCreateResponseDTO> create(
      @Valid @RequestBody OrderCreateRequestDTO request) {
    var order = orderService.create(request);
    return new SuccessResponse<>(order, HttpStatus.OK.value());
  }
}
