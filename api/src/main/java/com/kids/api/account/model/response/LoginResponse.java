package com.kids.api.account.model.response;

import com.kids.api.account.parents.Parents;
import com.kids.api.global.response.BasicResponse;

import lombok.Getter;

@Getter
public class LoginResponse extends BasicResponse {
    private boolean success;

    private String provider;
    
    private int id;

    private String name;

    public LoginResponse(String msg, int code, boolean success, String provider, String name, int id) {
        super(msg, code);
        this.success = success;
        this.provider = provider;
        this.name = name;
        this.id = id;
    }

    public static LoginResponse of(Parents parents) {
        return new LoginResponse("로그인에 성공했습니다",
                                 200,
                                 true,
                                 parents.getProvider(),
                                 parents.getName(),
                                 parents.getId());
    }
}
