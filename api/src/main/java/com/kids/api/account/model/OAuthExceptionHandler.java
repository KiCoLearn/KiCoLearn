package com.kids.api.account.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kids.api.account.model.exception.BasicOAuthException;
import com.kids.api.account.model.response.OAuthResponse;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class OAuthExceptionHandler {
    @ExceptionHandler(value = { BasicOAuthException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public OAuthResponse ads(BasicOAuthException e) {
        return OAuthResponse.of(e);
    }
    
    @ExceptionHandler(value = { ExpiredJwtException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object expire(ExpiredJwtException e) {
        return e.getMessage();
    }
}
