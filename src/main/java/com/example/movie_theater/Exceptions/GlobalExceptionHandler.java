package com.example.movie_theater.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException ex){
        Map<String,String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (ms1,ms2) -> ms1
                        ));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler (BusinnesException.class)
    public ResponseEntity<String> handleBussinesError(BusinnesException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
