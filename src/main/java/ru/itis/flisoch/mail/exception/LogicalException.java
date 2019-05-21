package ru.itis.flisoch.mail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LogicalException extends RuntimeException {

    public LogicalException(String message) {
        super(message);
    }

}