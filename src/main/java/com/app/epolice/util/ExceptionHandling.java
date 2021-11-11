package com.app.epolice.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * The type Exception handling.
 */
@ControllerAdvice
public class ExceptionHandling {
    /**
     * Handle validation exceptions map.
     *
     * @param ex the ex
     * @return the map
     */
/*
    public static ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
*/

    /**
     * Input validation exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, InvalidFormatException.class, HttpMessageNotReadableException.class, MissingRequestHeaderException.class, MissingPathVariableException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> inputValidationException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
