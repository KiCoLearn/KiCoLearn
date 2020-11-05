package com.kids.api.account.model.exception;

import lombok.Getter;

@Getter
public class InvalidProviderException extends BasicOAuthException {
    public InvalidProviderException(String message, String provider) {
        super(message, provider, -404);
    }

    private static final long serialVersionUID = -2328769713149202342L;
}
