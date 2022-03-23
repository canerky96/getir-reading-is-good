package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.dto.request.CustomerCreateRequestDTO;
import com.kaya.authorizationapi.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerCreateService {

  private static final List<String> DEFAULT_USER_PERMISSIONS =
      List.of("read_customer_order", "read_order_by_id", "create_order", "read_statistics");

  private final CustomerWriteService customerWriteService;
  private final PasswordEncoder passwordEncoder;

  public Customer create(CustomerCreateRequestDTO requestDTO) {

    var user =
        Customer.builder()
            .id(UUID.randomUUID().toString())
            .username(requestDTO.getUsername())
            .password(passwordEncoder.encode(requestDTO.getPassword()))
            .permissions(DEFAULT_USER_PERMISSIONS)
            .build();

    log.info(
        "User created, username: {}, permissions: {}", user.getUsername(), user.getPermissions());

    return customerWriteService.save(user);
  }
}
