package com.kaya.authorizationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAuthorizationServer
@EnableSwagger2
public class AuthorizationApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthorizationApiApplication.class, args);
  }
}
