package com.ecom.product.exceptionhandling;

import com.ecom.product.exception.NoResourceFoundException;
import com.ecom.product.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorResponse> noResourceFoundExceptionHandler(
      NoResourceFoundException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(false, exception.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(false, exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(
      MethodArgumentNotValidException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> dataIntegrityViolationExceptionHandler(
      DataIntegrityViolationException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(false, exception.getMessage()), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler(
      ConstraintViolationException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
