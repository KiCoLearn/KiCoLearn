package com.kids.api.oauth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kids.api.oauth.error.exception.BasicOAuthException;
import com.kids.api.oauth.error.exception.InvalidTokenException;

@RestControllerAdvice
public class OAuthExceptionHandler {
    @ExceptionHandler(value = { BasicOAuthException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public OAuthResponse ads(BasicOAuthException e) {
        return OAuthResponse.of(e);
    }
}
