package com.kaya.orderapi.service.order;

import com.kaya.orderapi.dto.request.OrderCreateRequestDTO;
import com.kaya.orderapi.dto.request.OrderQueryBetweenDatesRequestDTO;
import com.kaya.orderapi.dto.response.OrderCreateResponseDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.mapper.OrderMapper;
import com.kaya.orderapi.service.book.BookService;
import com.kaya.orderapi.service.orderedbook.OrderedBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final BookService bookService;
  private final OrderedBookService orderedBookService;

  private final OrderReadService orderReadService;
  private final OrderCreateService orderCreateService;
  private final OrderMapper mapper;

  @Transactional(readOnly = true)
  public Page<Order> searchByUsername(String username, Pageable pageable) {
    return orderReadService.findPageableByUsername(username, pageable);
  }

  @Transactional(readOnly = true)
  public List<Order> searchByUsername(String username) {
    return orderReadService.findAllByUsername(username);
  }

  @Transactional(rollbackFor = Exception.class)
  public OrderCreateResponseDTO create(OrderCreateRequestDTO request) {
    var books = bookService.findAllById(request.getBookIds());
    bookService.decreaseStocks(books);

    var order = orderCreateService.create();
    var orderedBooks = orderedBookService.create(order, books);

    order.setBooks(orderedBooks);
    return mapper.mapToCreateResponse(order);
  }

  @Transactional(readOnly = true)
  public OrderResponseDTO get(Long id) {
    var order = orderReadService.findById(id);
    return mapper.mapToResponse(order);
  }

  @Transactional(readOnly = true)
  public List<OrderResponseDTO> query(OrderQueryBetweenDatesRequestDTO queryParams) {
    var startDate = queryParams.getStartDate();
    var endDate = queryParams.getEndDate();
    var orders = orderReadService.findByBetweenDates(startDate, endDate);
    return orders.stream().map(mapper::mapToResponse).collect(Collectors.toList());
  }
}
