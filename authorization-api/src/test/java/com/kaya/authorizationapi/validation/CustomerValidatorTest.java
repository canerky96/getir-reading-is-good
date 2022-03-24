package com.kaya.authorizationapi.validation;

import com.kaya.authorizationapi.exception.AuthorizationApiException;
import com.kaya.authorizationapi.service.CustomerReadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerValidatorTest {

  @InjectMocks private CustomerValidator customerValidator;

  @Mock private CustomerReadService customerReadService;

  @Test(expected = AuthorizationApiException.class)
  public void shouldNotValidateForExistenceByUsername() {
    // given
    var username = "username";

    // when
    when(customerReadService.existsByUsername(anyString())).thenReturn(true);
    customerValidator.validateForExistenceByUsername(username);
    // verify
    verify(customerReadService, times(1)).existsByUsername(username);
  }

  @Test
  public void shouldValidateForExistenceByUsername() {
    // given
    var username = "username";

    // when
    when(customerReadService.existsByUsername(anyString())).thenReturn(false);
    customerValidator.validateForExistenceByUsername(username);
    // verify
    verify(customerReadService, times(1)).existsByUsername(username);
  }
}
