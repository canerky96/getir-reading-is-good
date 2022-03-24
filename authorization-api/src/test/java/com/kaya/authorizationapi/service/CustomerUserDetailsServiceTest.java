package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerUserDetailsServiceTest {

  @InjectMocks private CustomerUserDetailsService customerUserDetailsService;

  @Mock private CustomerService customerService;
  @Mock private AccountStatusUserDetailsChecker accountStatusUserDetailsChecker;

  @Test
  public void shouldLoadUserByUsername() {
    // given
    var username = "username";
    var customer = new Customer();
    // when
    when(customerService.findByUsername(anyString())).thenReturn(customer);

    var result = customerUserDetailsService.loadUserByUsername(username);
    // verify
    verify(customerService, times(1)).findByUsername(username);
    verify(accountStatusUserDetailsChecker, times(1)).check(customer);
    assertEquals(customer, result);
  }
}
