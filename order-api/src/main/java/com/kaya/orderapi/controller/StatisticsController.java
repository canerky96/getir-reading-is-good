package com.kaya.orderapi.controller;

import com.kaya.orderapi.dto.SuccessResponse;
import com.kaya.orderapi.dto.request.OrderStatisticsQueryParamDTO;
import com.kaya.orderapi.dto.response.OrderStatisticsResponseDTO;
import com.kaya.orderapi.service.statistic.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(StatisticsController.ENDPOINT)
@RequiredArgsConstructor
public class StatisticsController {

  public static final String ENDPOINT = "statistics";

  private final StatisticsService statisticsService;

  @GetMapping
  public SuccessResponse<List<OrderStatisticsResponseDTO>> search(
      @Valid OrderStatisticsQueryParamDTO query) {
    var statistics = statisticsService.search(query);
    return new SuccessResponse<>(statistics, HttpStatus.OK.value());
  }
}
