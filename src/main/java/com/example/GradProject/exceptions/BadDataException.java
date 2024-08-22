package com.example.GradProject.exceptions;

public class BadDataException extends RuntimeException {
    public BadDataException(String detail) {
        super(detail);
    }
}