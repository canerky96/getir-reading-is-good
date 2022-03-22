package com.kaya.authorizationapi.config;

import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.service.CustomerWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminConfiguration {

  private final AdminConfigurationProperties adminProperties;
  private final CustomerWriteService customerWriteService;

  @PostConstruct
  public void initializeAdmin() {
    var admin =
        Customer.builder()
            .id(adminProperties.getId())
            .username(adminProperties.getUsername())
            .password(adminProperties.getPassword())
            .permissions(adminProperties.getPermissions())
            .build();
    customerWriteService.save(admin);
  }
}
