package ru.itis.flisoch.mail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.flisoch.mail.exception.ExceptionDetails;
import ru.itis.flisoch.mail.exception.UserAlreadyExistsException;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<ExceptionDetails> handleUserAlreadyException(
            UserAlreadyExistsException exception,
            WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

}