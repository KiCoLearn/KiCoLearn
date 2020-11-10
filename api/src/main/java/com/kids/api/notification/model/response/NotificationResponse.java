package com.kids.api.notification.model.response;

import java.time.ZonedDateTime;

import com.kids.api.global.response.BasicResponse;
import com.kids.api.notification.FCMNotification;
import com.kids.api.notification.model.exception.NotificationFailedException;
import com.kids.api.notification.model.exception.NotificationNotFoundException;

import lombok.Getter;

@Getter
public class NotificationResponse extends BasicResponse {
    FCMNotification request;
    boolean success;

    public NotificationResponse(String msg,
                                int code,
                                ZonedDateTime stamp) {
        super(msg, code, stamp);
    }

    public NotificationResponse(String msg,
                                int code,
                                ZonedDateTime stamp,
                                FCMNotification request,
                                boolean success) {
        super(msg, code, stamp);
        this.request = request;
        this.success = success;
    }
    
    public static NotificationResponse of(NotificationFailedException e) {
        return new NotificationResponse(e.getMessage(),
                                        e.getCode(),
                                        e.getStamp(),
                                        e.getNotification(),
                                        false);
    }

    public static NotificationResponse of(NotificationNotFoundException e) {
        return new NotificationResponse(e.getMessage(),
                                        e.getCode(),
                                        e.getStamp(),
                                        e.getNotification(),
                                        false);
    }
}
