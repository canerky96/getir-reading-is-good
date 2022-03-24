package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerWriteServiceTest {

  @InjectMocks private CustomerWriteService customerWriteService;

  @Mock private CustomerRepository customerRepository;

  @Test
  public void shouldSave() {
    // given
    var customer = new Customer();
    // when
    when(customerRepository.save(any(Customer.class))).thenReturn(customer);

    var result = customerWriteService.save(customer);
    // verify
    verify(customerRepository, times(1)).save(customer);
    assertEquals(customer, result);
  }
}
