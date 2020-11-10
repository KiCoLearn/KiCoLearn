package com.kids.api.notification;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private String body;
    private String title;
    private String click_action;
    
    public JSONObject toJSON() {
        JSONObject body = new JSONObject();
        
        body.put("body", this.body);
        body.put("title", this.title);
        body.put("click_action", this.click_action);
            
        return body;
    }
}
