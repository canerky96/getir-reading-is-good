package com.kaya.authorizationapi.controller;

import com.kaya.authorizationapi.dto.request.UserCreateRequestDTO;
import com.kaya.authorizationapi.dto.response.UserCreateResponseDTO;
import com.kaya.authorizationapi.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(AuthUserController.ENDPOINT)
@RequiredArgsConstructor
public class AuthUserController {

  public static final String ENDPOINT = "user";

  private final AuthUserService authUserService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserCreateResponseDTO create(@Valid @RequestBody UserCreateRequestDTO request) {
    return authUserService.create(request);
  }
}
