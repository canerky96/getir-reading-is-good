package com.kaya.authorizationapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCreateServiceTest {
  @InjectMocks private CustomerCreateService customerCreateService;
  @Mock private CustomerWriteService customerWriteService;
  @Mock private PasswordEncoder passwordEncoder;

  @Test
  public void shouldCreate() {
    //when
  }
}
