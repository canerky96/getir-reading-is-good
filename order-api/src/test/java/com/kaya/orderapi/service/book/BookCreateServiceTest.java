package com.kaya.orderapi.service.book;

import com.kaya.orderapi.dto.request.BookCreateRequestDTO;
import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.mapper.BookMapper;
import com.kaya.orderapi.service.book.BookCreateService;
import com.kaya.orderapi.service.book.BookWriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookCreateServiceTest {

  @InjectMocks private BookCreateService bookCreateService;
  @Mock private BookMapper bookMapper;
  @Mock private BookWriteService bookWriteService;

  @Test
  public void shouldCreate() {
    // given
    var request = new BookCreateRequestDTO();
    var book = new Book();
    // when
    when(bookMapper.map(any(BookCreateRequestDTO.class))).thenReturn(book);
    when(bookWriteService.save(any(Book.class))).thenReturn(book);

    var result = bookCreateService.create(request);
    // verify
    verify(bookMapper, times(1)).map(request);
    verify(bookWriteService, times(1)).save(book);
    assertEquals(book, result);
  }
}
