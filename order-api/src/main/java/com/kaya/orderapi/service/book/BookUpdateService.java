package com.kaya.orderapi.service.book;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.exception.OrderApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kaya.orderapi.exception.ErrorCode.INVALID_BOOK_STOCK;

@Service
@RequiredArgsConstructor
public class BookUpdateService {

  private final BookWriteService bookWriteService;

  @Transactional(rollbackFor = Exception.class)
  public void decreaseStock(List<Book> books) {
    books.forEach(this::decreaseStock);
  }

  @Transactional(rollbackFor = Exception.class)
  public Book updateStock(Book book, Integer stock) {
    book.setStock(stock);
    return bookWriteService.save(book);
  }

  private void decreaseStock(Book book) {
    var currentStock = book.getStock();
    if (currentStock <= 0) {
      throw new OrderApiException(INVALID_BOOK_STOCK);
    }
    book.setStock(currentStock - 1);
    bookWriteService.save(book);
  }
}
