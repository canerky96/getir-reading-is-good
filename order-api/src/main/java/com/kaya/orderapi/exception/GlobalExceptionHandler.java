package com.kaya.orderapi.exception;

import com.kaya.orderapi.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    List<String> errors = new ArrayList<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
    var message = String.join(", ", errors);
    return ResponseEntity.status(ErrorCode.FIELD_VALIDATION_ERROR.getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(ErrorCode.FIELD_VALIDATION_ERROR.getCode())
                .message(message)
                .build());
  }

  @ExceptionHandler(BindException.class)
  protected ResponseEntity<ErrorResponse> handleQueryParamValidation(BindException ex) {
    List<String> errors = new ArrayList<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
    var message = String.join(", ", errors);
    return ResponseEntity.status(ErrorCode.FIELD_VALIDATION_ERROR.getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(ErrorCode.FIELD_VALIDATION_ERROR.getCode())
                .message(message)
                .build());
  }

  @ExceptionHandler(value = {OrderApiException.class})
  protected ResponseEntity<ErrorResponse> handleAuthorizationApiException(OrderApiException ex) {
    return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .build());
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
    return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ex.getMessage())
                .build());
  }
}
