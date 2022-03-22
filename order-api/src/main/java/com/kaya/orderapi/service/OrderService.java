package com.kaya.orderapi.service;

import com.kaya.orderapi.client.bookapi.BookApiEndpointService;
import com.kaya.orderapi.client.bookapi.dto.BookResponseDTO;
import com.kaya.orderapi.dto.request.OrderCreateRequestDTO;
import com.kaya.orderapi.dto.request.OrderQueryParamDTO;
import com.kaya.orderapi.dto.response.OrderCreateResponseDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.kaya.orderapi.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final BookApiEndpointService bookApiEndpointService;
  private final OrderCreateService orderCreateService;
  private final OrderWriteService orderWriteService;
  private final OrderReadService orderReadService;
  private final OrderMapper mapper;

  public OrderCreateResponseDTO create(OrderCreateRequestDTO request) {
    var books = bookApiEndpointService.findAllById(request.getBookIds());
    var order = orderCreateService.create(books);
    bookApiEndpointService.decreaseStocks(order.getBooks());
    order = orderWriteService.save(order);
    return mapper.mapToCreateResponse(order);
  }

  public List<OrderResponseDTO> search(OrderQueryParamDTO queryParams) {
    var orders = orderReadService.findAllByUsername(queryParams.getUsername());
    var bookIds =
        orders.stream().flatMap(order -> order.getBooks().stream()).collect(Collectors.toSet());
    var bookMap = bookApiEndpointService.findMapById(bookIds);
    return orders.stream().map(mapToResponse(bookMap)).collect(Collectors.toList());
  }

  public OrderResponseDTO get(Long id) {
    var order = orderReadService.findById(id);
    var books = bookApiEndpointService.findAllById(order.getBooks());
    return Optional.of(order)
        .map(mapToResponse(books))
        .orElseThrow(() -> new OrderApiException(INTERNAL_SERVER_ERROR));
  }

  private Function<Order, OrderResponseDTO> mapToResponse(List<BookResponseDTO> books) {
    return (order) -> {
      var response = mapper.mapToResponse(order);
      response.setBooks(books);
      return response;
    };
  }

  private Function<Order, OrderResponseDTO> mapToResponse(Map<Long, BookResponseDTO> books) {
    return (order) -> {
      var response = mapper.mapToResponse(order);
      List<BookResponseDTO> bookResponses = new ArrayList<>();
      for (Long id : order.getBooks()) {
        bookResponses.add(books.get(id));
      }
      response.setBooks(bookResponses);
      return response;
    };
  }
}
