package com.kids.api.notification.model.exception;

import com.kids.api.global.error.exception.BasicException;
import com.kids.api.notification.FCMNotification;

import lombok.Getter;

@Getter
public class NotificationNotFoundException extends BasicException {
    private static final long serialVersionUID = -9009327472793066929L;
    FCMNotification notification;

    public NotificationNotFoundException(FCMNotification notification, String message) {
        super(message, -400);
        this.notification = notification;
    }
}
