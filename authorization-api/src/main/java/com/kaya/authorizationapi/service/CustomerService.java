package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.dto.request.CustomerCreateRequestDTO;
import com.kaya.authorizationapi.dto.response.CustomerCreateResponseDTO;
import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.mapper.CustomerMapper;
import com.kaya.authorizationapi.validation.AuthUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerMapper mapper;
  private final CustomerReadService customerReadService;
  private final AuthUserValidator authUserValidator;
  private final CustomerCreateService customerCreateService;

  public Customer findByUsername(String username) {
    return customerReadService.findByUsername(username);
  }

  public CustomerCreateResponseDTO create(CustomerCreateRequestDTO requestDTO) {
    authUserValidator.validateForExistenceByUsername(requestDTO.getUsername());
    var user = customerCreateService.create(requestDTO);
    return mapper.map(user);
  }
}
