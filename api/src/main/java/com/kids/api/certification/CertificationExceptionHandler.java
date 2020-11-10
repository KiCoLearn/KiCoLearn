package com.kids.api.certification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kids.api.certification.exception.BasicCertificationException;
import com.kids.api.certification.response.CertificationResponse;


@RestControllerAdvice
public class CertificationExceptionHandler {
    @ExceptionHandler(value = { BasicCertificationException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public CertificationResponse ads(BasicCertificationException e) {
        return CertificationResponse.of(e);
    }
}
