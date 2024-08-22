package com.example.GradProject.exceptions.handlers;

import com.example.GradProject.exceptions.BadDataException;
import com.example.GradProject.exceptions.InsufficientVacationBalanceException;
import com.example.GradProject.exceptions.MissingDataException;
import com.example.GradProject.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<String> handleBadDataException(BadDataException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<String> handleMissingDataException(MissingDataException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInsufficientVacationBalanceException(InsufficientVacationBalanceException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public <E extends NotFoundException> ResponseEntity<String> handleNotFoundException(E ex){
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseEntity<String> handleErrorResponse(Exception ex){
//        log.error(ex.getMessage());
//        return ResponseEntity.internalServerError().body(ex.getMessage());
//    }

}