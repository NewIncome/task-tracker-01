package com.jalfredev.tasktracker.controllers;

import com.jalfredev.tasktracker.domain.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  // to handle 'IllegalArgumentException'
  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<ErrorResponse> handleExceptions(
          RuntimeException ex, WebRequest request
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(), //400
        ex.getMessage(),  //typically we need to be careful not to expose internal details through this message
        request.getDescription(false)
    );

    // Here the errorResponse is converted to json
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
