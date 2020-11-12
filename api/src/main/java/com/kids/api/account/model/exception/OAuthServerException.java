package com.kids.api.account.model.exception;

import lombok.Getter;

@Getter
public class OAuthServerException extends BasicOAuthException {
    public OAuthServerException(String message, String provider) {
        super(message, provider, -1);
    }

    private static final long serialVersionUID = -2328769713149202342L;
}
