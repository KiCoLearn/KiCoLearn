package com.kids.api.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kids.api.global.response.MissingParameterResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MissingParameterResponse ads(MissingServletRequestParameterException e) {
        return MissingParameterResponse.of(e);
    }
    
}
