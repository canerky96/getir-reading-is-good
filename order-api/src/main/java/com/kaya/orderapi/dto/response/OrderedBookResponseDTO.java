package com.kaya.orderapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedBookResponseDTO {

  private String name;
  private String writer;
  private Long price;
}
