package com.kaya.orderapi.service.book;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.repository.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

import static com.kaya.orderapi.exception.ErrorCode.BOOKS_NOT_FOUND;
import static com.kaya.orderapi.exception.ErrorCode.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookReadService {

  private final BookRepository bookRepository;

  public boolean existsByNameAndWriter(@NonNull String name, @NonNull String writer) {
    return bookRepository.existsByNameAndWriter(name, writer);
  }

  public Book findById(Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new OrderApiException(BOOK_NOT_FOUND));
  }

  public List<Book> findAllByIdIn(Collection<Long> ids) {
    var books = bookRepository.findAllByIdIn(ids);
    if (CollectionUtils.isEmpty(books)) {
      throw new OrderApiException(BOOKS_NOT_FOUND);
    }
    return books;
  }
}
