package com.kaya.orderapi.service.book;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.repository.BookRepository;
import com.kaya.orderapi.service.book.BookWriteService;
import com.kaya.orderapi.utils.SecurityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookWriteServiceTest {

  @InjectMocks private BookWriteService bookWriteService;

  @Mock private BookRepository bookRepository;
  @Mock private SecurityUtils securityUtils;

  @Test
  public void shouldSave() {
    var book = new Book();

    when(bookRepository.save(any(Book.class))).thenReturn(book);

    var result = bookWriteService.save(book);

    verify(bookRepository).save(book);
    assertEquals(book, result);
  }
}
