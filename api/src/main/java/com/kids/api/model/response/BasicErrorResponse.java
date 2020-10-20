package com.kids.api.model.response;

import java.time.LocalDateTime;

import com.kids.api.model.exception.BasicException;

import lombok.Getter;

@Getter
public class BasicErrorResponse {
    public LocalDateTime time;
    public String message;

    public BasicErrorResponse(LocalDateTime time, String message) {
        super();
        this.time = time;
        this.message = message;
    }

    public static BasicErrorResponse of(BasicException e) {
        return new BasicErrorResponse(
                                      e.stamp,
                                      e.getMessage());
    }

}
