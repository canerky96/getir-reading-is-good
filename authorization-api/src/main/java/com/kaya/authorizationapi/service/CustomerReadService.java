package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.exception.AuthorizationApiException;
import com.kaya.authorizationapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kaya.authorizationapi.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustomerReadService {

  private final CustomerRepository customerRepository;

  public Customer findByUsername(String username) {
    return findOptionalByUsername(username).orElseThrow(() -> new AuthorizationApiException(USER_NOT_FOUND));
  }

  public Optional<Customer> findOptionalByUsername(String username) {
    return customerRepository.findByUsername(username);
  }

  public boolean existsByUsername(String username) {
    return customerRepository.existsByUsername(username);
  }
}
