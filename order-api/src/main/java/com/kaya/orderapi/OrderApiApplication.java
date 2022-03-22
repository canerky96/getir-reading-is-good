package com.kaya.orderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
public class OrderApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderApiApplication.class, args);
  }
}
