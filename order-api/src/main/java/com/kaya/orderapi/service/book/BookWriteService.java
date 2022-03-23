package com.kaya.orderapi.service.book;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.repository.BookRepository;
import com.kaya.orderapi.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookWriteService {

  private final BookRepository bookRepository;

  public Book save(Book book) {
    log.info("Book inserted from username:{}, book: {}", SecurityUtils.getUsername(), book);
    return bookRepository.save(book);
  }
}
