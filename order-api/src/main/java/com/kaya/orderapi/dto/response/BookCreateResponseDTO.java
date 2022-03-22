package com.kaya.orderapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateResponseDTO {

  private Long id;
  private Date createDate;
  private String name;
  private String writer;
  private Long price;
  private Integer stock;
}
