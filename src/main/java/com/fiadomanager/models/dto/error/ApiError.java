package com.fiadomanager.models.dto.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {

    private HttpStatus httpStatus;
    private String errorMessage;

}
