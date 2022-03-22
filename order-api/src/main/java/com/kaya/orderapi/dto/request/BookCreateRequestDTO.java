package com.kaya.orderapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequestDTO {

  @NotBlank(message = "Name field is required")
  private String name;

  @NotBlank(message = "Writer field is required")
  private String writer;

  @NotNull(message = "Price field is required")
  @Min(value = 0, message = "Price should be greater than 0")
  private Long price;

  @NotNull(message = "Stock field is required")
  @Min(value = 0, message = "Stock should be greater than 0")
  private Integer stock;
}
