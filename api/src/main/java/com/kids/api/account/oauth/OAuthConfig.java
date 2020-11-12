package com.kids.api.account.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OAuthConfig implements WebMvcConfigurer {

    @Autowired
    private OAuthTokenInterceptor oAuthTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(oAuthTokenInterceptor)
                .addPathPatterns("/api/kidsaccount/**")
                .addPathPatterns("/api/money/**")
                .addPathPatterns("/api/parents/**")
                .excludePathPatterns("/api/parents/login/**")
                .addPathPatterns("/api/quest/**")
                .addPathPatterns("/api/quiz/**")
                .addPathPatterns("/api/store/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Provider");
    }
}
