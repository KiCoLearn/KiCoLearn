package com.kids.api.global.response;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Getter;

@Getter
public class BasicResponse {
    ZonedDateTime stamp;
    String msg;
    int code;

    public BasicResponse(String msg, int code, ZonedDateTime stamp) {
        super();
        this.msg = msg;
        this.code = code;
        this.stamp = stamp;
    }
    
    public BasicResponse(String msg, int code) {
        this(msg, code, new Date().toInstant().atZone(ZoneId.of("Asia/Seoul")));
    }
}
