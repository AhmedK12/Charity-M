package com.example.helptek.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UserException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public Map<String,String> methodArgumentNotValidException(ConstraintViolationException exception){
              return  exception
                      .getConstraintViolations()
                      .stream()
                      .collect(Collectors
                              .toMap(constraintViolation -> constraintViolation
                                              .getPropertyPath().toString()
                                      , ConstraintViolation::getMessage));

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public Map<String,String> SQLException(SQLIntegrityConstraintViolationException exception){
        Map<String,String> response =  new HashMap<>();
        response.put("Timestamp", ZonedDateTime.now(ZoneId.of("Z")).toString());
        response.put("ErrorCode:","404");
        response.put("Error",exception.getMessage());
        return response;

    }




}
