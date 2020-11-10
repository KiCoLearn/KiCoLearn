package com.kids.api.certification.response;

import java.time.ZonedDateTime;

import com.kids.api.certification.exception.BasicCertificationException;
import com.kids.api.global.response.BasicResponse;

public class CertificationResponse extends BasicResponse {

    public CertificationResponse(String msg, int code, ZonedDateTime stamp) {
        super(msg, code, stamp);
    }
    public static CertificationResponse of(BasicCertificationException e) {
        return new CertificationResponse(e.getMessage(), e.getCode(), e.getStamp());
    }

}
