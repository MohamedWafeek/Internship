package com.example.GradProject.exceptions;

public class InsufficientVacationBalanceException extends RuntimeException {
    public InsufficientVacationBalanceException(String insufficientVacationBalance) {
        super(insufficientVacationBalance);
    }
}
