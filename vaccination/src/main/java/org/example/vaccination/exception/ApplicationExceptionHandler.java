package org.example.vaccination.exception;

import org.example.vaccination.exception.dto.DataViolationException;
import org.example.vaccination.exception.dto.ExceptionDTO;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleArgumentNotValid(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(
                errors
                        .stream()
                        .map(ExceptionDTO::new)
                        .toList()
        );
    }

    @ExceptionHandler(DataViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataViolationException ex) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("message", ex.getMessage());
        payload.put("status", HttpStatus.CONFLICT);

        return new ResponseEntity<>(payload, HttpStatus.CONFLICT);
    }
}
