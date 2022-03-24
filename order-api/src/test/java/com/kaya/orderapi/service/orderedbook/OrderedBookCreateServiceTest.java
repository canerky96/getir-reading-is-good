package com.kaya.orderapi.service.orderedbook;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.OrderedBook;
import com.kaya.orderapi.mapper.OrderedBookMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderedBookCreateServiceTest {

  @InjectMocks private OrderedBookCreateService orderedBookCreateService;

  @Mock private OrderedBookMapper orderedBookMapper;

  @Test
  public void shouldCreate() {
    var order = new Order();
    var book = new Book();
    var books = List.of(book);
    var orderedBook = new OrderedBook();
    var orderedBooks = List.of(orderedBook);

    when(orderedBookMapper.map(any(Book.class))).thenReturn(orderedBook);

    var result = orderedBookCreateService.create(order, books);
    verify(orderedBookMapper, times(books.size())).map(book);
    assertEquals(orderedBooks, result);
  }
}
