package com.github.virginonline.planetsandlords.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableSpringDataWebSupport
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionResponse body = new ExceptionResponse(status,"Invalid Json");

        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
        TypeMismatchException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ){
        String format = "Parameter '%s' of value '%s' could not be converted to type '%s'";
        ExceptionResponse body = new ExceptionResponse(status,String.format(
                format,ex.getPropertyName(),
                ex.getValue(),
                ex.getRequiredType())
        );
        return new ResponseEntity<>(body,headers,status);
    }
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        ExceptionResponse body = new ExceptionResponse(status,"Endpoint not found on path : \"" + ex.getRequestURL() + "\"");
        return new ResponseEntity<>(body,headers,status);
    }

    @ExceptionHandler({LordNotFoundException.class,PlanetNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleLordNotFoundException(Exception ex){
        ExceptionResponse body = new ExceptionResponse(HttpStatus.NOT_FOUND,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
