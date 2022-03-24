package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.dto.request.CustomerCreateRequestDTO;
import com.kaya.authorizationapi.dto.response.CustomerCreateResponseDTO;
import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.mapper.CustomerMapper;
import com.kaya.authorizationapi.validation.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerMapper mapper;
  private final CustomerReadService customerReadService;
  private final CustomerValidator customerValidator;
  private final CustomerCreateService customerCreateService;

  public Customer findByUsername(String username) {
    return customerReadService.findByUsername(username);
  }

  public CustomerCreateResponseDTO create(CustomerCreateRequestDTO requestDTO) {
    customerValidator.validateForExistenceByUsername(requestDTO.getUsername());
    var customer = customerCreateService.create(requestDTO);
    return mapper.map(customer);
  }
}
