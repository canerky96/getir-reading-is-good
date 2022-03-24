package com.kaya.orderapi.service.book;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.repository.BookRepository;
import com.kaya.orderapi.service.book.BookReadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookReadServiceTest {

  @InjectMocks private BookReadService bookReadService;

  @Mock private BookRepository bookRepository;

  @Test
  public void shouldExistsByNameAndWriter() {
    // given
    var name = "name";
    var writer = "writer";
    // when
    when(bookRepository.existsByNameAndWriter(anyString(), anyString())).thenReturn(true);
    // verify
    assertTrue(bookReadService.existsByNameAndWriter(name, writer));
    verify(bookRepository, times(1)).existsByNameAndWriter(name, writer);
  }

  @Test
  public void shouldFindById() {
    var id = 1L;
    var book = new Book();

    when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

    assertEquals(book, bookReadService.findById(id));
    verify(bookRepository, times(1)).findById(id);
  }

  @Test(expected = OrderApiException.class)
  public void shouldNotFindById() {
    var id = 1L;

    when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

    bookReadService.findById(id);
    verify(bookRepository).findById(id);
  }

  @Test
  public void shouldFindAllByIdIn() {
    var ids = List.of(1L);
    var books = List.of(new Book());

    when(bookRepository.findAllByIdIn(anyCollection())).thenReturn(books);

    var result = bookReadService.findAllByIdIn(ids);
    assertEquals(books, result);
    verify(bookRepository).findAllByIdIn(ids);
  }

  @Test(expected = OrderApiException.class)
  public void shouldNotFindAllByIdIn() {
    var ids = List.of(1L);
    List<Book> books = new ArrayList<>();

    when(bookRepository.findAllByIdIn(anyCollection())).thenReturn(books);

    bookReadService.findAllByIdIn(ids);
    verify(bookRepository).findAllByIdIn(ids);
  }
}
