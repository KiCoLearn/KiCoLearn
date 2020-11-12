package com.kids.api.certification.exception;

public class CodeNotFoundException extends BasicCertificationException {

    private static final long serialVersionUID = -5502409435035833067L;

    public CodeNotFoundException(String message) {
        // 인증코드 불일치 오류
        super(message, -101);

    }

}
