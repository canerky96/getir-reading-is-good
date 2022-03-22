package com.kaya.orderapi.controller;

import com.kaya.orderapi.dto.SuccessResponse;
import com.kaya.orderapi.dto.request.OrderQueryParamDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(CustomerController.ENDPOINT)
@RequiredArgsConstructor
public class CustomerController {

  public static final String ENDPOINT = "customer";

  private final CustomerService customerService;

  @GetMapping
  @PreAuthorize("hasAuthority('read_order')")
  public SuccessResponse<Page<OrderResponseDTO>> search(@Valid OrderQueryParamDTO queryParams) {
    var orders = customerService.search(queryParams);
    return new SuccessResponse<>(orders, HttpStatus.OK.value());
  }
}
