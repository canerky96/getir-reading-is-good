package com.kaya.authorizationapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequestDTO {

  @NotBlank(message = "Username field is required")
  private String username;

  @NotBlank(message = "Password field is required")
  private String password;
}
