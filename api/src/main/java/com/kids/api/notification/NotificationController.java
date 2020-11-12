package com.kids.api.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    
    @PostMapping("")
    public ResponseEntity<Object> alert(@RequestBody FCMNotification notification) {
        notificationService.sendNotification(notification);

        return ResponseEntity.accepted().build();
    }
}
