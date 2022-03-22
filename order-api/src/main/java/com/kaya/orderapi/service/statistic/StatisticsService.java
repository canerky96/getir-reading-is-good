package com.kaya.orderapi.service.statistic;

import com.kaya.orderapi.dto.request.OrderStatisticsQueryParamDTO;
import com.kaya.orderapi.dto.response.OrderStatisticsResponseDTO;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.OrderedBook;
import com.kaya.orderapi.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

  private final OrderService orderService;

  @Transactional(readOnly = true)
  public List<OrderStatisticsResponseDTO> search(OrderStatisticsQueryParamDTO query) {
    var orders = orderService.searchByUsername(query.getUsername());

    Map<String, List<Order>> groupingByMonth =
        orders.stream().collect(Collectors.groupingBy(groupByMonth()));

    return generateResponse(groupingByMonth);
  }

  private Function<Order, String> groupByMonth() {
    return (order) -> {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(order.getCreateDate());
      return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    };
  }

  private List<OrderStatisticsResponseDTO> generateResponse(
      Map<String, List<Order>> groupingByMonth) {

    List<OrderStatisticsResponseDTO> responseList = new ArrayList<>();

    for (String month : groupingByMonth.keySet()) {
      var statistic = OrderStatisticsResponseDTO.builder().month(month).build();
      var monthlyOrder = groupingByMonth.get(month);
      var monthlyBooks =
          monthlyOrder.stream()
              .flatMap(order -> order.getBooks().stream())
              .collect(Collectors.toList());
      var totalPurchasedAmount = monthlyBooks.stream().mapToLong(OrderedBook::getPrice).sum();
      statistic.setTotalOrderCount(monthlyOrder.size());
      statistic.setTotalBookCount(monthlyBooks.size());
      statistic.setTotalPurchasedAmount(totalPurchasedAmount);
      responseList.add(statistic);
    }

    return responseList;
  }
}
