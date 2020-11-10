package com.kids.api.notification;

import org.json.JSONObject;

import com.kids.api.notification.model.exception.NotificationNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class FCMNotification {
    private String to;
    private JSONObject data;
    private Notification notification;
    
    public JSONObject toJSON() {
        if (this.notification == null) {
            throw new NotificationNotFoundException(this, "cannot found notification");
        }
        JSONObject body = new JSONObject();
        
        body.put("to", this.to);
        body.put("notification", this.notification.toJSON());
            
        return body;
    }
    
    
}
