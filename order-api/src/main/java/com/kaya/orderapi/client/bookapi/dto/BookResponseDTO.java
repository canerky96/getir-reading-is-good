package com.kaya.orderapi.client.bookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {

  private Long id;
  private String name;
  private String writer;
  private Long price;
  private Integer stock;
}
