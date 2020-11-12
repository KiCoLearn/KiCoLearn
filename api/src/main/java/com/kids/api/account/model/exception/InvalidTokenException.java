package com.kids.api.account.model.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends BasicOAuthException {
    public InvalidTokenException(String message, String provider) {
        super(message, provider, -401);
    }

    private static final long serialVersionUID = -2328769713149202342L;
}
