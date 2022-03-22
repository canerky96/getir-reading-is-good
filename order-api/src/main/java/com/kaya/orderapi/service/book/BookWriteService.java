package com.kaya.orderapi.service.book;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookWriteService {

  private final BookRepository bookRepository;

  @Transactional(rollbackFor = Exception.class)
  public Book save(Book book) {
    return bookRepository.save(book);
  }
}
