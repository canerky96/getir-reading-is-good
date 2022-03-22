package com.kaya.orderapi.exception;

import lombok.Getter;

@Getter
public class OrderApiException extends RuntimeException {

    private final ErrorCode errorCode;

    public OrderApiException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
