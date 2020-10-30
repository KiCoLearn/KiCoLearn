package com.kids.api.global.error.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {
    public int code;
    public ZonedDateTime stamp;
    private static final long serialVersionUID = -4988279375045328027L;

    public BasicException(String message) {
        super(message);
        this.stamp = new Date().toInstant().atZone(ZoneId.of("Asia/Seoul"));
        this.code = -1;
    }
    
    public BasicException(String message, int code) {
        super(message);
        this.stamp = new Date().toInstant().atZone(ZoneId.of("Asia/Seoul"));
        this.code = code;
    }
}
