package com.kaya.orderapi.service.book;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.service.book.BookUpdateService;
import com.kaya.orderapi.service.book.BookWriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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
public class BookUpdateServiceTest {

  @InjectMocks private BookUpdateService bookUpdateService;

  @Mock private BookWriteService bookWriteService;

  @Test
  public void shouldDecreaseStock() {
    var stock = 2;
    var book = Book.builder().stock(stock).build();
    var books = List.of(book);
    var argumentCaptor = ArgumentCaptor.forClass(Book.class);

    bookUpdateService.decreaseStock(books);

    verify(bookWriteService, times(books.size())).save(argumentCaptor.capture());
    assertEquals(stock - 1, argumentCaptor.getValue().getStock());
  }

  @Test(expected = OrderApiException.class)
  public void shouldNotDecreaseStock() {
    var stock = 0;
    var book = Book.builder().stock(stock).build();
    var books = List.of(book);
    bookUpdateService.decreaseStock(books);
  }

  @Test
  public void shouldUpdateStock() {
    var book = new Book();
    var stock = 10;

    when(bookWriteService.save(any(Book.class))).thenReturn(book);

    var result = bookUpdateService.updateStock(book, stock);

    assertEquals(stock, result.getStock());
  }
}
