package com.kaya.orderapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryBetweenDatesRequestDTO {

  @NotNull(message = "startDate is required")
  @DateTimeFormat(pattern = "dd.MM.yyyy")
  private Date startDate;

  @NotNull(message = "endDate is required")
  @DateTimeFormat(pattern = "dd.MM.yyyy")
  private Date endDate;
}
