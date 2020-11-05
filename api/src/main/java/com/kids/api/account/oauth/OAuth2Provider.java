package com.kids.api.account.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.json.JSONException;
import org.json.JSONObject;

import com.kids.api.account.model.exception.InvalidTokenException;
import com.kids.api.account.model.exception.OAuthServerException;

public enum OAuth2Provider {
    KAKAO {
        @Override
        public JSONObject getTokenInformation(String accessToken) throws IOException {
            URL url = new URL("https://kapi.kakao.com/v1/user/access_token_info");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", String.format("Bearer %s", accessToken));
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            JSONObject response = request(connection);
            
            return response;
        }

        @Override
        public JSONObject logout(String accessToken) throws IOException {
            URL url = new URL("https://kapi.kakao.com/v1/user/logout");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", String.format("Bearer %s", accessToken));

            JSONObject response = request(connection);
            
            return response;
        }

        @Override
        public JSONObject getUserInformation(String accessToken) throws IOException {
            URL url = new URL("https://kapi.kakao.com/v2/user/me");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", String.format("Bearer %s", accessToken));
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            JSONObject response = request(connection);
            
            return response;
        }

        @Override
        public JSONObject unlink(String accessToken) throws IOException {
            URL url = new URL("https://kapi.kakao.com/v1/user/unlink");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", String.format("Bearer %s", accessToken));

            JSONObject response = request(connection);
            
            return response;
        }
    };
    
    private static JSONObject request(HttpURLConnection connection) {
        try {
            connection.connect();
        
            int responseCode = connection.getResponseCode();
            if (200 <= responseCode && responseCode < 300) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
    
                JSONObject response = new JSONObject(sb.toString());
    
                return response;
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
    
                JSONObject response = new JSONObject(sb.toString());
    
                try {
                    int code = response.getInt("code");
                    switch (code) {
                    case -1:
                        throw new OAuthServerException("please try again", "kakao");
                    case -2:
                    case -401:
                        throw new InvalidTokenException(response.getString("msg"), "kakao");
                    default: // 유효하지 않은,,
                        throw new OAuthServerException("please try again", "kakao");
                    }
                } catch (JSONException e) {
                    throw new OAuthServerException("please try again", "kakao");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new OAuthServerException("please try again", "kakao");
        }
    }

    public abstract JSONObject unlink(String accessToken) throws IOException;
    public abstract JSONObject logout(String accessToken) throws IOException;
    public abstract JSONObject getTokenInformation(String accessToken) throws IOException;
    public abstract JSONObject getUserInformation(String accessToken) throws IOException;
}
