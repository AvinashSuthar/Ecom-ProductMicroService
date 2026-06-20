package com.ecom.product.exceptionhandling;

import com.ecom.product.exception.NoResourceFoundException;
import com.ecom.product.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> noResourceFoundExceptionHandler(NoResourceFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(false, exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(new ErrorResponse(false, exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
