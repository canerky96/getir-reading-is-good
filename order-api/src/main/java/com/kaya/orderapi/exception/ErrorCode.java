package com.kaya.orderapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  INTERNAL_SERVER_ERROR(1, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
  FIELD_VALIDATION_ERROR(2, "Field validation error.", HttpStatus.BAD_REQUEST),
  ORDERS_NOT_FOUND_WITH_USERNAME(3, "No result found with given username", HttpStatus.NOT_FOUND),
  ORDER_NOT_FOUND(4, "Order not found", HttpStatus.NOT_FOUND);

  private final int code;
  private final String message;
  private final HttpStatus httpStatus;
}
