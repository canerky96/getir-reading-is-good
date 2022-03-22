package com.kaya.orderapi.client.bookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookQueryRequest {
  @NotEmpty(message = "At least 1 id required")
  private Collection<Long> ids;
}
