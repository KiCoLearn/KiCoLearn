package com.kids.api.oauth;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.kids.api.oauth.error.exception.InvalidProviderException;

@Service
public class OAuth2Service {
    public long getUserId(String token, String provider) {
        switch (provider.toLowerCase()) {
        case "kakao":
            try {
                JSONObject userInfo = OAuth2Provider.KAKAO.getInformation(token);

                long id = userInfo.getLong("id");
                int expires_in = userInfo.getInt("expires_in");
                int app_id = userInfo.getInt("app_id");

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
}
