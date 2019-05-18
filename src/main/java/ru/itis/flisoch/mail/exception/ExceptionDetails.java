package ru.itis.flisoch.mail.exception;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ExceptionDetails {
    private LocalDateTime time;
    private String message;
    private String details;
}