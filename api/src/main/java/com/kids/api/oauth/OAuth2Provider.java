package com.kids.api.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.kids.api.oauth.error.exception.InvalidTokenException;
import com.kids.api.oauth.error.exception.OAuthServerException;

public enum OAuth2Provider {
    KAKAO {
        @Override
        public JSONObject getInformation(String accessToken) throws IOException {
            URL url = new URL("https://kapi.kakao.com/v1/user/access_token_info");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", String.format("Bearer %s", accessToken));
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

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
        }

        @Override
        public JSONObject logout(String accessToken) throws IOException {
            URL url = new URL("https://kapi.kakao.com/v1/user/logout");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", String.format("Bearer %s", accessToken));

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
        }
    };

    public abstract JSONObject logout(String accessToken) throws IOException;
    public abstract JSONObject getInformation(String accessToken) throws IOException;
}
