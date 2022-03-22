package com.kaya.authorizationapi.controller;

import com.kaya.authorizationapi.dto.request.CustomerCreateRequestDTO;
import com.kaya.authorizationapi.dto.response.CustomerCreateResponseDTO;
import com.kaya.authorizationapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(CustomerController.ENDPOINT)
@RequiredArgsConstructor
public class CustomerController {

  public static final String ENDPOINT = "customer";

  private final CustomerService customerService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerCreateResponseDTO create(@Valid @RequestBody CustomerCreateRequestDTO request) {
    return customerService.create(request);
  }
}
