package com.kaya.authorizationapi.service;

import com.kaya.authorizationapi.dto.request.UserCreateRequestDTO;
import com.kaya.authorizationapi.dto.response.UserCreateResponseDTO;
import com.kaya.authorizationapi.entity.AuthUser;
import com.kaya.authorizationapi.mapper.AuthUserMapper;
import com.kaya.authorizationapi.validation.AuthUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService {

  private final AuthUserMapper mapper;
  private final AuthUserReadService authUserReadService;
  private final AuthUserValidator authUserValidator;
  private final AuthUserCreateService authUserCreateService;

  public AuthUser findByUsername(String username) {
    return authUserReadService.findByUsername(username);
  }

  public UserCreateResponseDTO create(UserCreateRequestDTO requestDTO) {
    authUserValidator.validateForExistenceByUsername(requestDTO.getUsername());
    var user = authUserCreateService.create(requestDTO);
    return mapper.map(user);
  }
}
