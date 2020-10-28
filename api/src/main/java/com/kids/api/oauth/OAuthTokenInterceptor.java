package com.kids.api.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kids.api.oauth.error.exception.InvalidTokenException;

@Component
public class OAuthTokenInterceptor implements HandlerInterceptor {
    @Autowired
    OAuth2Service oauthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println(request.getMethod() + " : " + request.getServletPath());

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            String token = request.getHeader("Authorization");
            if (token == null) {
                throw new InvalidTokenException("cannot find token", null);
            }
            String provider = request.getHeader("Provider");
            if (provider == null) {
                throw new InvalidTokenException("cannot find provider", null);
            }

            long uid = oauthService.getUserId(token, provider);

            return uid != -1;
        }
    }
}
