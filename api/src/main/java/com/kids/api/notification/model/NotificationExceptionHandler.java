package com.kids.api.notification.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kids.api.notification.model.exception.NotificationFailedException;
import com.kids.api.notification.model.exception.NotificationNotFoundException;
import com.kids.api.notification.model.response.NotificationResponse;

@RestControllerAdvice
public class NotificationExceptionHandler {
    @ExceptionHandler(value = { NotificationFailedException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public NotificationResponse ads(NotificationFailedException e) {
        return NotificationResponse.of(e);
    }
    
    @ExceptionHandler(value = { NotificationNotFoundException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public NotificationResponse ads(NotificationNotFoundException e) {
        return NotificationResponse.of(e);
    }
}
