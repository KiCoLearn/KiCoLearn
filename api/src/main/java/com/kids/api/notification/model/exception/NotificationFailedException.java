package com.kids.api.notification.model.exception;

import com.kids.api.global.error.exception.BasicException;
import com.kids.api.notification.FCMNotification;

import lombok.Getter;
import lombok.Setter;

public class NotificationFailedException extends BasicException {
    private static final long serialVersionUID = -9009327472793066929L;
    
    @Getter @Setter
    FCMNotification notification;

    public NotificationFailedException(FCMNotification notification, String message) {
        super(message, -500);
        this.notification = notification;
    }
}
