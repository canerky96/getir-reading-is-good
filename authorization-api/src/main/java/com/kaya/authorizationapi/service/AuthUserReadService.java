package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.entity.AuthUser;
import com.kaya.authorizationapi.exception.AuthorizationApiException;
import com.kaya.authorizationapi.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kaya.authorizationapi.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthUserReadService {

  private final AuthUserRepository authUserRepository;

  public AuthUser findByUsername(String username) {
    return findOptionalByUsername(username).orElseThrow(() -> new AuthorizationApiException(USER_NOT_FOUND));
  }

  public Optional<AuthUser> findOptionalByUsername(String username) {
    return authUserRepository.findByUsername(username);
  }

  public boolean existsByUsername(String username) {
    return authUserRepository.existsByUsername(username);
  }
}
