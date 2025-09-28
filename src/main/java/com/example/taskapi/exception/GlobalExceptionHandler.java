package com.example.taskapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAny(Exception ex) {
        return ResponseEntity.status(500).body(Map.of(
            "timestamp", Instant.now().toString(),
            "error", ex.getMessage()
        ));
    }
}
