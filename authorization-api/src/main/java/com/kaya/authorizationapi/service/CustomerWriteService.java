package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerWriteService {

  private final CustomerRepository customerRepository;

  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }
}
