package com.kaya.authorizationapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "authorization-api.admin")
public class AdminConfigurationProperties {

  private String id;
  private String username;
  private String password;
  private List<String> permissions;
}
