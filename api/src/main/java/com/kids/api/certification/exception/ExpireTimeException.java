package com.kids.api.certification.exception;

public class ExpireTimeException extends BasicCertificationException {

    private static final long serialVersionUID = 1081817412344178194L;

    public ExpireTimeException(String message) {
        // 인증번호 시간 초과 오류
        super(message, -100);
    }

}
