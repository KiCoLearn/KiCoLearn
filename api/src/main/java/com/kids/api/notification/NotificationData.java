package com.kids.api.notification;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class NotificationData {
    String message;

    public Object toJSON() {
        JSONObject body = new JSONObject();
        
        body.put("message", this.message);
            
        return body;
    }
}
