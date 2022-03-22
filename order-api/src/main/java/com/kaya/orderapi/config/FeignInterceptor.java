package com.kaya.orderapi.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@Configuration
public class FeignInterceptor {

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      OAuth2AuthenticationDetails details =
          (OAuth2AuthenticationDetails) authentication.getDetails();
      requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());
    };
  }
}
