package com.kids.api.account.oauth;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kids.api.account.User;
import com.kids.api.account.model.exception.InvalidProviderException;
import com.kids.api.jwt.JwtService;

import lombok.NonNull;

@Service
public class OAuth2Service {
    public long getUserId(String token, String provider) {
        switch (provider.toLowerCase()) {
        case "kakao":
            try {
                JSONObject tokenInfo = OAuth2Provider.KAKAO.getTokenInformation(token);

                long id = tokenInfo.getLong("id");

                return id;
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        default:
            throw new InvalidProviderException("invailid provider", provider);
        }

        return -1;
    }
    
    @Autowired
    JwtService jwtService;
    
    public boolean isValid(String token, String provider) {
        switch (provider.toLowerCase()) {
        case "kakao":
            return getUserId(token, provider)!=-1;
        case "kicolearn" :
            return jwtService.checkValid(token);
        default:
            throw new InvalidProviderException("invailid provider", provider);
        }

    }
    
    public User getUserInformation(String token, String provider) {
        switch (provider.toLowerCase()) {
        case "kakao":
            try {
                JSONObject userInfo = OAuth2Provider.KAKAO.getUserInformation(token);

                System.out.println(userInfo.toString());
                long id = userInfo.getLong("id");
                String nickname = userInfo.getJSONObject("kakao_account").getJSONObject("profile").getString("nickname");

                return User.builder()
                                .uuid(id)
                                .name(nickname)
                                .provider(provider)
                                .build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        default:
            throw new InvalidProviderException("invailid provider", provider);
        }

        return null;
    }
    
    public long logout(String token, String provider) {
        switch (provider.toLowerCase()) {
        case "kakao":
            try {
                JSONObject userInfo = OAuth2Provider.KAKAO.logout(token);

                long id = userInfo.getLong("id");

                return id;
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        default:
            throw new InvalidProviderException("invailid provider", provider);
        }

        return -1;
    }

    public long unlink(@NonNull String token, String provider) {
        switch (provider.toLowerCase()) {
        case "kakao":
            try {
                JSONObject userInfo = OAuth2Provider.KAKAO.unlink(token);

                long id = userInfo.getLong("id");

                return id;
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        default:
            throw new InvalidProviderException("invailid provider", provider);
        }

        return -1;
    }
}
