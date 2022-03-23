package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.exception.AuthorizationApiException;
import com.kaya.authorizationapi.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerReadServiceTest {

  @InjectMocks private CustomerReadService customerReadService;

  @Mock private CustomerRepository customerRepository;

  @Test
  public void shouldFindByUsername() {
    // given
    var username = "user";
    var customer = new Customer();
    // when
    when(customerRepository.findByUsername(anyString())).thenReturn(Optional.of(customer));

    var result = customerReadService.findByUsername(username);

    // verify
    verify(customerRepository).findByUsername(username);
    assertEquals(customer, result);
  }

  @Test(expected = AuthorizationApiException.class)
  public void shouldNotFindByUsername() {
    // given
    var username = "user";
    var customer = new Customer();
    // when
    when(customerRepository.findByUsername(anyString())).thenReturn(Optional.empty());

    customerReadService.findByUsername(username);

    // verify
    verify(customerRepository).findByUsername(username);
  }

  @Test
  public void shouldExistsByUsername() {
    // given
    var username = "user";
    // when
    when(customerRepository.existsByUsername(anyString())).thenReturn(true);
    var result = customerReadService.existsByUsername(username);
    // verify
    verify(customerRepository).existsByUsername(username);
    assertTrue(result);
  }
}
