package com.kids.api.jwt;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kids.api.certification.KidsAuth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    public String create(final KidsAuth user) {
        final JwtBuilder builder = Jwts.builder();
        
        builder.setHeaderParam("type", "JWT");

        builder.setSubject("로그인 토큰") // 토근 제목 설정
        .setExpiration(new Date(System.currentTimeMillis() + 1000*60 *expireMin)) // 유효기간
        .claim("User", user);

        builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

        final String jwt = builder.compact();
        log.trace("토큰 발행: {}", jwt); 
        return jwt; 
    }

    public boolean checkValid(final String jwt) { 

        log.trace("토큰 점검 : {}", jwt); 
        Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt); 
        return true;
    } 

    public Map<String, Object>get(final String jwt){ 
        Jws<Claims> claims = null; 
        try { 
            claims =Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt); 
        }catch(final Exception e) { 
            throw new RuntimeException(); 
        } 

        log.trace("claims: {}", claims); 
        // Claims는 Map의 구현체이다. 
        return claims.getBody(); 
    } 


}