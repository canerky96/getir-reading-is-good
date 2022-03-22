package com.kaya.authorizationapi.validation;

import com.kaya.authorizationapi.exception.AuthorizationApiException;
import com.kaya.authorizationapi.exception.ErrorCode;
import com.kaya.authorizationapi.service.CustomerReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserValidator {

  private final CustomerReadService customerReadService;

  public void validateForExistenceByUsername(String username) {
    if (customerReadService.existsByUsername(username)) {
      throw new AuthorizationApiException(ErrorCode.USER_CONFLICT);
    }
  }
}
