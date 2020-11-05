package com.kids.api.account.model.response;

import java.time.ZonedDateTime;

import com.kids.api.account.model.exception.BasicOAuthException;
import com.kids.api.global.response.BasicResponse;

import lombok.Getter;

@Getter
public class OAuthResponse extends BasicResponse {
    private long validity;
    private String provider;

    public OAuthResponse(String msg, int code, ZonedDateTime stamp, String provider) {
        super(msg, code, stamp);
        this.provider = provider;
        this.validity = -1;
    }
    
    public OAuthResponse(long uid, String provider) {
        super("valid account", 200);
        this.provider = provider;
        this.validity = uid;
    }
    
    public static OAuthResponse of(Long uid, String provider) {
        return new OAuthResponse(uid, provider);
    }

    public static OAuthResponse of(BasicOAuthException e) {
        return new OAuthResponse(e.getMessage(), e.getCode(), e.getStamp(), e.getProvider());
    }
}
