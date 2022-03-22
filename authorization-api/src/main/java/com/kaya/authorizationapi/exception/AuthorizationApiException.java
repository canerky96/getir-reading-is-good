package com.kaya.authorizationapi.exception;

import lombok.Getter;

@Getter
public class AuthorizationApiException extends RuntimeException {

  private final ErrorCode errorCode;

  public AuthorizationApiException(ErrorCode errorCode) {
    super();
    this.errorCode = errorCode;
  }
}
