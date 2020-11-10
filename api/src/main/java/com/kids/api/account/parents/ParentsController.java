package com.kids.api.account.parents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kids.api.account.AccountService;
import com.kids.api.account.model.response.LoginResponse;
import com.kids.api.account.oauth.OAuth2Service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.NonNull;

@RestController
@RequestMapping("/api/parents")
public class ParentsController {
    @Autowired
    OAuth2Service oauthService;

    @Autowired
    AccountService<Parents> parentsAccountService;

    @ApiOperation("로그인")
    @ApiResponses({
                    @ApiResponse(code = 200, message = "유효한 계정"),
                    @ApiResponse(code = 401,
                                 message = "유효하지 않은 토큰"),
                    @ApiResponse(code = 404,
                                 message = "유효하지 않은 제공자") })
    @PostMapping("/login/{provider}")
    public LoginResponse login(
                    @ApiParam(value = "발급받은 엑세스 토큰") @RequestParam @NonNull String token,
                    @ApiParam(value = "OAuth 제공자", example = "kakao") @PathVariable String provider) {
        long uuid = oauthService.getUserId(token, provider);

        System.out.println("uuid " + uuid);

        Parents parents = parentsAccountService.login(Parents.builder()
                                                             .token(token)
                                                             .uuid(uuid)
                                                             .provider(provider)
                                                             .build());

        return LoginResponse.of(parents);
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

    @ApiOperation("회원탈퇴")
    @ApiResponses({
                    @ApiResponse(code = 204, message = "성공적으로 로그아웃되었음"),
                    @ApiResponse(code = 401,
                                 message = "유효하지 않은 토큰"),
                    @ApiResponse(code = 404,
                                 message = "유효하지 않은 제공자") })
    @PostMapping("/unlink/{provider}")
    public ResponseEntity<Object> unlink(
                    @ApiParam(value = "발급받은 엑세스 토큰") @RequestParam @NonNull String token,
                    @ApiParam(value = "OAuth 제공자", example = "kakao") @PathVariable String provider) {
        long uuid = oauthService.getUserId(token, provider);
        parentsAccountService.delete(Parents.builder()
                                            .token(token)
                                            .uuid(uuid)
                                            .provider(provider)
                                            .build());

        uuid = oauthService.unlink(token, provider);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String getKidsList2() {
        return "Hello, World!";
    }

    @ApiOperation("메시징 ID 등록")
    @Authorization(value = "role")
    @PostMapping("/accounts/{id}/messaging-token")
    public ResponseEntity<Object> postMessagingToken(
                    @PathVariable int id,
                    @RequestBody Parents parent) {
        System.out.println(parent.toString());
        boolean result = parentsAccountService.updateToken(id, parent.getMessagingToken());
        
        return ResponseEntity.noContent().build();
    }
}
