package com.kaya.orderapi.validation;

import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.service.book.BookReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.kaya.orderapi.exception.ErrorCode.BOOK_CONFLICT;

@Component
@RequiredArgsConstructor
public class BookValidator {

  private final BookReadService bookReadService;

  public void validateForExistenceByNameAndWriter(String name, String writer) {
    if (bookReadService.existsByNameAndWriter(name, writer)) {
      throw new OrderApiException(BOOK_CONFLICT);
    }
  }
}
