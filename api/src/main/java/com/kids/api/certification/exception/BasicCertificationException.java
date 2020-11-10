package com.kids.api.certification.exception;

import com.kids.api.global.error.exception.BasicException;

public class BasicCertificationException extends BasicException {

    private static final long serialVersionUID = 889338788802633465L;

    public BasicCertificationException(String message, int code) {
        super(message, code);
    }

}
