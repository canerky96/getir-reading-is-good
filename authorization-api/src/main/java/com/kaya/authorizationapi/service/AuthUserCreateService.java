package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.dto.request.UserCreateRequestDTO;
import com.kaya.authorizationapi.entity.AuthUser;
import com.kaya.authorizationapi.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUserCreateService {

  private static final List<String> DEFAULT_USER_PERMISSIONS =
      List.of("decrease_book_stock", "read_book", "read_order", "create_order");

  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(rollbackFor = Exception.class)
  public AuthUser create(UserCreateRequestDTO requestDTO) {

    var user =
        AuthUser.builder()
            .username(requestDTO.getUsername())
            .password(passwordEncoder.encode(requestDTO.getPassword()))
            .permissions(DEFAULT_USER_PERMISSIONS)
            .build();

    log.info(
        "User created, username: {}, permissions: {}", user.getUsername(), user.getPermissions());

    return authUserRepository.save(user);
  }
}
