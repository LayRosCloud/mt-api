package com.trans.api.scripts.handlers;

import com.trans.api.scripts.exception.BadRequestException;
import com.trans.api.scripts.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex){
        ResponseEntity<Object> response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
    @ExceptionHandler({NotFoundException.class, BadRequestException.class})
    public ResponseEntity<Object> handleCustomException(ResponseStatusException ex){
        ResponseEntity<Object> response = new ResponseEntity<>(ex.getBody(), ex.getStatusCode());
        return response;
    }


}
