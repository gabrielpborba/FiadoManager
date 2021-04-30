package com.fiadomanager.models.exception;

import com.fiadomanager.models.dto.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FiadoManagerCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FiadoManagerCustomException.class)
    public ResponseEntity<ApiError> handlerFiadoCustomException(FiadoManagerCustomException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(ex.getMessage());
        apiError.setHttpStatus(ex.getHttpStatus());
        return ResponseEntity.status(ex.getHttpStatus()).body(apiError);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerAllExceptions(Exception ex) {
        ApiError apiError = new ApiError();
        apiError.setErrorMessage(ex.getCause().getCause().getMessage());
        apiError.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiError);

    }

}
