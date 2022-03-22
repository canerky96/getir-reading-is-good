package com.kaya.orderapi.dto.response;

import com.kaya.orderapi.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponseDTO {

  private Long id;
  private String username;
  private OrderStatus status;
  private List<OrderedBookResponseDTO> books;
}
