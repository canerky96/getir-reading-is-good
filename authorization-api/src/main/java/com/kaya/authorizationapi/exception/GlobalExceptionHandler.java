package com.kaya.authorizationapi.exception;

import com.kaya.authorizationapi.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    var message = String.join(", ", errors.values());
    return ResponseEntity.status(ErrorCode.FIELD_VALIDATION_ERROR.getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(ErrorCode.FIELD_VALIDATION_ERROR.getCode())
                .message(message)
                .build());
  }

  @ExceptionHandler(value = {AuthorizationApiException.class})
  protected ResponseEntity<ErrorResponse> handleAuthorizationApiException(AuthorizationApiException ex) {
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
