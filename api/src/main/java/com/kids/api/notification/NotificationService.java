package com.kids.api.notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kids.api.account.AccountService;
import com.kids.api.account.model.exception.InvalidTokenException;
import com.kids.api.account.model.exception.OAuthServerException;
import com.kids.api.account.parents.Parents;
import com.kids.api.global.util.Connection;
import com.kids.api.kidsaccount.Kids;
import com.kids.api.kidsaccount.KidsService;
import com.kids.api.notification.model.exception.NotificationFailedException;

@Service
public class NotificationService {
    @Value("${fcm.server-key}")
    String FCM_SERVER_KEY;

    @Value("${custom.server-url}")
    String SERVER_URL;

    @Autowired
    KidsService kService;

    @Autowired
    AccountService<Parents> parentsAccountService;

    public void completeQuestFromKidId(int kidId) {
        Kids kid = kService.detailKid(kidId);
        String token = parentsAccountService.getTokenById(kid.getParentId());
        FCMNotification notification = FCMNotification.builder()
                                                      .to(token)
                                                      .notification(Notification.builder()
                                                                                .title(kid.getName() + "이(가) 퀘스트를 완료했어요!")
                                                                                .body("확인해주세요")
                                                                                .click_action("parents/quest")
                                                                                .build())
                                                      .data(NotificationData.builder()
                                                                            .message("합니다")
                                                                            .build())
                                                      .build();

        this.sendNotification(notification);
    }

    public void itemPurchaseRequest(int kidId) {
        Kids kid = kService.detailKid(kidId);
        String token = parentsAccountService.getTokenById(kid.getParentId());
        FCMNotification notification = FCMNotification.builder()
                                                      .to(token)
                                                      .notification(Notification.builder()
                                                                                .title(kid.getName() + "이(가) 아이템 구매를 원합니다!")
                                                                                .body("확인해주세요!")
                                                                                .click_action("parents/store")
                                                                                .build())
                                                      .data(NotificationData.builder()
                                                                            .message("스토어에서 구매 수락해주세요!")
                                                                            .build())
                                                      .build();
        this.sendNotification(notification);

    }

    public void sendNotification(FCMNotification notification) {
        try {
            Connection connection = Connection.builder("post", "https://fcm.googleapis.com/fcm/send")
                                              .header("Content-Type", "application/json")
                                              .header("Authorization", String.format("key=%s", FCM_SERVER_KEY))
                                              .header("Accept", "application/json")
                                              .body(notification.toJSON())
                                              .build();

            JSONObject response = request(connection.getConnection());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotificationFailedException e) {
            if (e.getNotification() == null) {
                e.setNotification(notification);
            }
            throw e;
        }
    }

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

                System.out.println(response.toString());

                int success = response.getInt("success");

                if (success != 1) {
                    JSONArray results = response.getJSONArray("results");
                    String error = results.getJSONObject(0).getString("error");
                    throw new NotificationFailedException(null, error);
                }

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

}
