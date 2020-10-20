package com.kids.api.model.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class BasicException extends RuntimeException {
    public LocalDateTime stamp;
    private static final long serialVersionUID = -4988279375045328027L;

    public BasicException(String message) {
        super(message);
        this.stamp = new Date().toInstant().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }
}
