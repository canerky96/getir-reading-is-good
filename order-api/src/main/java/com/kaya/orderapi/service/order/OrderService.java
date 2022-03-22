package com.kaya.orderapi.service.order;

import com.kaya.orderapi.dto.request.OrderCreateRequestDTO;
import com.kaya.orderapi.dto.response.OrderCreateResponseDTO;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.mapper.OrderMapper;
import com.kaya.orderapi.service.book.BookService;
import com.kaya.orderapi.service.orderedbook.OrderedBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final BookService bookService;
  private final OrderedBookService orderedBookService;

  private final OrderReadService orderReadService;
  private final OrderCreateService orderCreateService;
  private final OrderWriteService orderWriteService;
  private final OrderMapper mapper;

  public Page<Order> searchByUsername(String username, Pageable pageable) {
    return orderReadService.findPageableByUsername(username, pageable);
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

  // public OrderResponseDTO get(Long id) {
  //  var order = orderReadService.findById(id);
  //  var books = bookApiEndpointService.findAllById(order.getBooks());
  //  return Optional.of(order)
  //      .map(mapToResponse(books))
  //      .orElseThrow(() -> new OrderApiException(INTERNAL_SERVER_ERROR));
  // }
  //
  // private Function<Order, OrderResponseDTO> mapToResponse(List<BookResponseDTO> books) {
  //  return (order) -> {
  //    var response = mapper.mapToResponse(order);
  //    response.setBooks(books);
  //    return response;
  //  };
  // }
  //
  // private Function<Order, OrderResponseDTO> mapToResponse(Map<Long, BookResponseDTO> books) {
  //  return (order) -> {
  //    var response = mapper.mapToResponse(order);
  //    List<BookResponseDTO> bookResponses = new ArrayList<>();
  //    for (Long id : order.getBooks()) {
  //      bookResponses.add(books.get(id));
  //    }
  //    response.setBooks(bookResponses);
  //    return response;
  //  };
  // }
}
