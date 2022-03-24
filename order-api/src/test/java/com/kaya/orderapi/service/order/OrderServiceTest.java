package com.kaya.orderapi.service.order;

import com.kaya.orderapi.dto.request.OrderCreateRequestDTO;
import com.kaya.orderapi.dto.request.OrderQueryBetweenDatesRequestDTO;
import com.kaya.orderapi.dto.response.OrderCreateResponseDTO;
import com.kaya.orderapi.dto.response.OrderResponseDTO;
import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.OrderedBook;
import com.kaya.orderapi.mapper.OrderMapper;
import com.kaya.orderapi.service.book.BookService;
import com.kaya.orderapi.service.orderedbook.OrderedBookService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

  @InjectMocks private OrderService orderService;

  @Mock private BookService bookService;
  @Mock private OrderedBookService orderedBookService;
  @Mock private OrderReadService orderReadService;
  @Mock private OrderCreateService orderCreateService;
  @Mock private OrderMapper mapper;

  @Test
  public void shouldSearchByUsernameWithPageable() {
    var username = "username";
    var pageable = PageRequest.of(0, 10);
    var orders = List.of(new Order());
    var pageOrders = new PageImpl<>(orders, pageable, orders.size());

    when(orderReadService.findPageableByUsername(anyString(), any(Pageable.class)))
        .thenReturn(pageOrders);

    var result = orderService.searchByUsername(username, pageable);
    verify(orderReadService).findPageableByUsername(username, pageable);
    assertEquals(pageOrders, result);
  }

  @Test
  public void shouldSearchByUsername() {
    var orders = List.of(new Order());
    var username = "username";

    when(orderReadService.findAllByUsername(anyString())).thenReturn(orders);

    var result = orderService.searchByUsername(username);
    verify(orderReadService).findAllByUsername(username);
    assertEquals(orders, result);
  }

  @Test
  public void shouldCreate() {
    var request = OrderCreateRequestDTO.builder().bookIds(Set.of(1L, 2L)).build();
    var books = List.of(new Book());
    var order = new Order();
    var orderedBooks = List.of(new OrderedBook());
    var orderCreateResponse = new OrderCreateResponseDTO();
    var argumentCaptor = ArgumentCaptor.forClass(Order.class);

    when(bookService.findAllById(anySet())).thenReturn(books);
    when(orderCreateService.create()).thenReturn(order);
    when(orderedBookService.create(any(Order.class), anyList())).thenReturn(orderedBooks);
    when(mapper.mapToCreateResponse(any())).thenReturn(orderCreateResponse);

    var result = orderService.create(request);

    verify(bookService).findAllById(request.getBookIds());
    verify(bookService).decreaseStocks(books);
    verify(orderCreateService).create();
    verify(orderedBookService).create(order, books);
    verify(mapper).mapToCreateResponse(argumentCaptor.capture());
    assertEquals(orderCreateResponse, result);
    assertEquals(orderedBooks, argumentCaptor.getValue().getBooks());
  }

  @Test
  public void shouldGet() {
    var id = 1L;
    var order = new Order();
    var response = new OrderResponseDTO();

    when(orderReadService.findById(anyLong())).thenReturn(order);
    when(mapper.mapToResponse(any(Order.class))).thenReturn(response);

    var result = orderService.get(id);

    verify(orderReadService).findById(id);
    verify(mapper).mapToResponse(order);
    assertEquals(response, result);
  }

  @SneakyThrows
  @Test
  public void shouldQuery() {
    Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
    Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022");
    var queryParams =
        OrderQueryBetweenDatesRequestDTO.builder().startDate(startDate).endDate(endDate).build();
    var order = new Order();
    var orders = List.of(order);
    var orderResponse = new OrderResponseDTO();

    when(orderReadService.findByBetweenDates(any(Date.class), any(Date.class))).thenReturn(orders);
    when(mapper.mapToResponse(any(Order.class))).thenReturn(orderResponse);

    var result = orderService.query(queryParams);
    verify(orderReadService)
        .findByBetweenDates(queryParams.getStartDate(), queryParams.getEndDate());
    verify(mapper).mapToResponse(order);
    assertEquals(List.of(orderResponse), result);
  }
}
