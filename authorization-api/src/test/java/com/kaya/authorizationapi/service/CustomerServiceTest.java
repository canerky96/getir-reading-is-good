package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.dto.request.CustomerCreateRequestDTO;
import com.kaya.authorizationapi.dto.response.CustomerCreateResponseDTO;
import com.kaya.authorizationapi.entity.Customer;
import com.kaya.authorizationapi.mapper.CustomerMapper;
import com.kaya.authorizationapi.validation.CustomerValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

  @InjectMocks private CustomerService customerService;

  @Mock private CustomerMapper customerMapper;
  @Mock private CustomerReadService customerReadService;
  @Mock private CustomerValidator customerValidator;
  @Mock private CustomerCreateService customerCreateService;

  @Test
  public void shouldFindByUsername() {
    // given
    var username = "username";
    var customer = new Customer();
    // when
    when(customerReadService.findByUsername(anyString())).thenReturn(customer);

    var result = customerService.findByUsername(username);
    // verify
    verify(customerReadService, times(1)).findByUsername(username);
    assertEquals(customer, result);
  }

  @Test
  public void shouldCreate() {
    // given
    var request =
        CustomerCreateRequestDTO.builder().username("username").password("password").build();
    var response = new CustomerCreateResponseDTO();
    var customer = new Customer();
    // when
    when(customerCreateService.create(any(CustomerCreateRequestDTO.class))).thenReturn(customer);
    when(customerMapper.map(any(Customer.class))).thenReturn(response);

    var result = customerService.create(request);
    // verify
    verify(customerValidator, times(1)).validateForExistenceByUsername(request.getUsername());
    verify(customerCreateService, times(1)).create(request);
    verify(customerMapper, times(1)).map(customer);
    assertEquals(response, result);
  }
}
