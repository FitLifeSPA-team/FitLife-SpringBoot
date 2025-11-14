package com.example.fitlifeSpring.exception;

import jakarta.validation.ConstraintViolationException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class globalexceptionhandler {

    @ExceptionHandler(notfoundexception.class)
    public ResponseEntity<Map<String,Object>> notFound(notfoundexception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,Object>> constraint(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(Map.of("errors", ex.getMessage()));
    }
}
