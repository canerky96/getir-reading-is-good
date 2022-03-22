package com.kaya.orderapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatisticsResponseDTO {

  private String month;
  private Integer totalOrderCount;
  private Integer totalBookCount;
  private Long totalPurchasedAmount;
}
