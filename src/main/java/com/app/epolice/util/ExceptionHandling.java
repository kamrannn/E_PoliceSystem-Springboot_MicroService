package com.app.epolice.util;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.firewall.RequestRejectedException;
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
     * Input validation exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler({RequestRejectedException.class, JsonMappingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class, InvalidFormatException.class, HttpMessageNotReadableException.class, MissingRequestHeaderException.class, MissingPathVariableException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> inputValidationException(Exception e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
