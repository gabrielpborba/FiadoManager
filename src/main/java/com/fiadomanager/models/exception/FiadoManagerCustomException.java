package com.fiadomanager.models.exception;

import org.springframework.http.HttpStatus;

public class FiadoManagerCustomException extends Exception {

    private HttpStatus httpStatus;
    private String message;

    public FiadoManagerCustomException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public FiadoManagerCustomException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public FiadoManagerCustomException(String message, Throwable cause, HttpStatus httpStatus, String message1) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public FiadoManagerCustomException(Throwable cause, HttpStatus httpStatus, String message) {
        super(cause);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public FiadoManagerCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
