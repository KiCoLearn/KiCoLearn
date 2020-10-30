package com.kids.api.global.response;

import org.springframework.web.bind.MissingServletRequestParameterException;

import lombok.Getter;

@Getter
public class MissingParameterResponse extends BasicResponse {
    public MissingParameterResponse(String msg, int code) {
        super(msg, code);
    }
    
    public static MissingParameterResponse of(MissingServletRequestParameterException e) {
        return new MissingParameterResponse(e.getMessage(), -1);
    }
}
