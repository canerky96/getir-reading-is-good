package com.kaya.authorizationapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  INTERNAL_SERVER_ERROR(1, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
  FIELD_VALIDATION_ERROR(2, "Field validation error.", HttpStatus.BAD_REQUEST),

  USER_NOT_FOUND(3, "User not found", HttpStatus.NOT_FOUND),
  USER_CONFLICT(4, "User already created", HttpStatus.CONFLICT);

  private final int code;
  private final String message;
  private final HttpStatus httpStatus;
}
