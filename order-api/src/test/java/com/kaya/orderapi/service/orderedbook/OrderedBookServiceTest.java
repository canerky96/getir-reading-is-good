package com.kaya.orderapi.service.orderedbook;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.OrderedBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderedBookServiceTest {

  @InjectMocks public OrderedBookService orderedBookService;
  @Mock private OrderedBookCreateService orderedBookCreateService;
  @Mock private OrderedBookWriteService orderedBookWriteService;

  @Test
  public void shouldCreate() {
    var order = new Order();
    var book = new Book();
    var books = List.of(book);
    var orderedBook = new OrderedBook();
    var orderedBooks = List.of(orderedBook);

    when(orderedBookCreateService.create(any(Order.class), anyList())).thenReturn(orderedBooks);
    when(orderedBookWriteService.saveAll(anyList())).thenReturn(orderedBooks);

    var result = orderedBookService.create(order, books);
    verify(orderedBookCreateService).create(order, books);
    verify(orderedBookWriteService).saveAll(orderedBooks);
    assertEquals(orderedBooks, result);
  }
}
