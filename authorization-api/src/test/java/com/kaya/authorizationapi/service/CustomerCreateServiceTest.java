package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.dto.request.CustomerCreateRequestDTO;
import com.kaya.authorizationapi.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCreateServiceTest {
  @InjectMocks private CustomerCreateService customerCreateService;
  @Mock private CustomerWriteService customerWriteService;
  @Mock private PasswordEncoder passwordEncoder;

  @Test
  public void shouldCreate() {
    // given
    var request =
        CustomerCreateRequestDTO.builder().username("username").password("password").build();
    var encodedPassword = "encodedPassword";
    ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);
    var customer = new Customer();
    // when
    when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
    when(customerWriteService.save(any(Customer.class))).thenReturn(customer);

    var result = customerCreateService.create(request);
    // verify
    verify(passwordEncoder).encode(request.getPassword());
    verify(customerWriteService, times(1)).save(argumentCaptor.capture());
    assertEquals(encodedPassword, argumentCaptor.getValue().getPassword());
    assertEquals(request.getUsername(), argumentCaptor.getValue().getUsername());
    assertEquals(customer, result);
  }
}
