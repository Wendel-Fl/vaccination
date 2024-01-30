package org.example.vaccination.exception.dto;

public class DataViolationException extends RuntimeException {

    public DataViolationException(String message) {
        super(message);
    }
}
