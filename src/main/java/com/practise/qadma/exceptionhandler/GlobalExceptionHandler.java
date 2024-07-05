package com.practise.qadma.exceptionhandler;

import com.practise.qadma.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(ItemNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(ex.getCustomErrorMessage());


        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
