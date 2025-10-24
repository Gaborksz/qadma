package com.practise.qadma.exceptionhandler;

import com.practise.qadma.exception.InspectionTemplateNotSavedException;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.exception.UserExistException;
import com.practise.qadma.exception.UserNotFoundException;
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

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleUserExistException(UserExistException ex) {

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleInspectionTemplateNotSaved(InspectionTemplateNotSavedException ex) {

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
   }
