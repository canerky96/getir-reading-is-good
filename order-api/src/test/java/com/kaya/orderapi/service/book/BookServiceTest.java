package com.kaya.orderapi.service.book;

import com.kaya.orderapi.dto.request.BookCreateRequestDTO;
import com.kaya.orderapi.dto.request.BookUpdateStockRequestDTO;
import com.kaya.orderapi.dto.response.BookCreateResponseDTO;
import com.kaya.orderapi.dto.response.BookResponseDTO;
import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.mapper.BookMapper;
import com.kaya.orderapi.service.book.BookCreateService;
import com.kaya.orderapi.service.book.BookReadService;
import com.kaya.orderapi.service.book.BookService;
import com.kaya.orderapi.service.book.BookUpdateService;
import com.kaya.orderapi.validation.BookValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

  @InjectMocks private BookService bookService;

  @Mock private BookMapper bookMapper;
  @Mock private BookValidator bookValidator;
  @Mock private BookCreateService bookCreateService;
  @Mock private BookReadService bookReadService;
  @Mock private BookUpdateService bookUpdateService;

  @Test
  public void shouldCreate() {
    var request = BookCreateRequestDTO.builder().name("name").writer("writer").build();
    var book = new Book();
    var response = new BookCreateResponseDTO();

    when(bookCreateService.create(any(BookCreateRequestDTO.class))).thenReturn(book);
    when(bookMapper.mapToCreateResponse(any(Book.class))).thenReturn(response);

    var result = bookService.create(request);

    verify(bookValidator)
        .validateForExistenceByNameAndWriter(request.getName(), request.getWriter());
    verify(bookCreateService).create(request);
    verify(bookMapper).mapToCreateResponse(book);
    assertEquals(response, result);
  }

  @Test
  public void shouldUpdateStock() {
    var id = 123L;
    var request = BookUpdateStockRequestDTO.builder().stock(1).build();
    var book = new Book();
    var response = new BookResponseDTO();

    when(bookReadService.findById(anyLong())).thenReturn(book);
    when(bookUpdateService.updateStock(any(Book.class), anyInt())).thenReturn(book);
    when(bookMapper.mapToBookResponse(any(Book.class))).thenReturn(response);

    var result = bookService.updateStock(id, request);

    verify(bookReadService).findById(id);
    verify(bookUpdateService).updateStock(book, request.getStock());
    verify(bookMapper).mapToBookResponse(book);
    assertEquals(response, result);
  }

  @Test
  public void shouldFindAllById() {
    var books = List.of(new Book());
    var ids = Set.of(1L);

    when(bookReadService.findAllByIdIn(anyCollection())).thenReturn(books);

    var result = bookService.findAllById(ids);

    verify(bookReadService).findAllByIdIn(ids);
    assertEquals(books, result);
  }

  @Test
  public void shouldDecreaseStocks() {
    var books = List.of(new Book());

    bookService.decreaseStocks(books);

    verify(bookUpdateService, times(1)).decreaseStock(books);
  }
}
