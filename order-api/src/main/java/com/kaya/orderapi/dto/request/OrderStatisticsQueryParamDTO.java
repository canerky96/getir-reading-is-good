package com.kaya.orderapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatisticsQueryParamDTO {

  @NotEmpty(message = "Username must be required")
  private String username;
}
