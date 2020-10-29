package com.kids.api.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kids.api.oauth.error.OAuthResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;

@RestController
public class OAuth2Controller {
    @Autowired
    OAuth2Service oauthService;

    @ApiOperation("계정 유효성 검증")
    @ApiResponses({
                    @ApiResponse(code = 200, message = "유효한 계정"),
                    @ApiResponse(code = 401,
                                 message = "유효하지 않은 토큰"),
                    @ApiResponse(code = 404,
                                 message = "유효하지 않은 제공자") })
    @GetMapping("/token/{provider}")
    public OAuthResponse getInfo(
                    @ApiParam(value = "발급받은 엑세스 토큰") @RequestParam @NonNull String token,
                    @ApiParam(value = "OAuth 제공자", example = "kakao") @PathVariable String provider) {

        return OAuthResponse.of(oauthService.getUserId(token, provider), provider);
    }
    
    @ApiOperation("로그아웃 중계")
    @ApiResponses({
                    @ApiResponse(code = 204, message = "성공적으로 로그아웃되었음"),
                    @ApiResponse(code = 401,
                                 message = "유효하지 않은 토큰"),
                    @ApiResponse(code = 404,
                                 message = "유효하지 않은 제공자") })
    @PostMapping("/logout/{provider}")
    public ResponseEntity<Object> logout(
                    @ApiParam(value = "발급받은 엑세스 토큰") @RequestParam @NonNull String token,
                    @ApiParam(value = "OAuth 제공자", example = "kakao") @PathVariable String provider) {

        oauthService.logout(token, provider);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/test")
    public String getKidsList2() {
        return "Hello, World!";
    }
    
}
