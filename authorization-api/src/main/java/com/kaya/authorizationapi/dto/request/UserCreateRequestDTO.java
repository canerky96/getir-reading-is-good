package com.kaya.authorizationapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDTO {

  @NotBlank(message = "Username field is required")
  private String username;

  @NotBlank(message = "Password field is required")
  private String password;
}
