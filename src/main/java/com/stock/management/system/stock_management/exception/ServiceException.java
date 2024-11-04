package com.stock.management.system.stock_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    @Getter
    private HttpStatus httpStatus;
    @Getter
    private String headerMessage;

    public ServiceException(String message, String headerMessage, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.headerMessage = headerMessage;
    }

}
