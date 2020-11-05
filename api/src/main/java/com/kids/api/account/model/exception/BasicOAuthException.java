package com.kids.api.account.model.exception;

import com.kids.api.global.error.exception.BasicException;

import lombok.Getter;

@Getter
public class BasicOAuthException extends BasicException {
    private String provider;
    
    public BasicOAuthException(String message, String provider, int code) {
        super(message, code);
        this.provider = provider;
    }

    private static final long serialVersionUID = -2328769713149202342L;
}
