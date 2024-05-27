package com.WeekXII.challenger3.exceptions;

import com.WeekXII.challenger3.errors.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValueAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleValueAlreadyExistsException(ValueAlreadyExistsException exception,
                                                                          HttpServletRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                          HttpServletRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND.value(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdValueOutOfBoundException.class)
    public ResponseEntity<ErrorDetails> handleIdValueOutOfBoundException(IdValueOutOfBoundException exception,
                                                                          HttpServletRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StatusAlreadyDisabledException.class)
    public ResponseEntity<ErrorDetails> handleStatusAlreadyDisabledException(StatusAlreadyDisabledException exception,
                                                                         HttpServletRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
