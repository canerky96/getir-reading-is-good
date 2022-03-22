package com.kaya.authorizationapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {

  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final LettuceConnectionFactory redisConnectionFactory;

  @Bean
  public TokenStore tokenStore() {
    return new RedisTokenStore(redisConnectionFactory);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer client) throws Exception {
    client
        .inMemory()
        .withClient("web")
        .secret(passwordEncoder.encode("web"))
        .scopes("READ", "WRITE")
        .authorizedGrantTypes("password", "authorization_code");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoint) throws Exception {
    endpoint.authenticationManager(authenticationManager);
    endpoint.tokenStore(tokenStore());
  }
}
