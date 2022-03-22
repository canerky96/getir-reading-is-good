package com.kaya.orderapi.service.orderedbook;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.OrderedBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderedBookService {

  private final OrderedBookCreateService orderedBookCreateService;
  private final OrderedBookWriteService orderedBookWriteService;

  @Transactional(rollbackFor = Exception.class)
  public List<OrderedBook> create(Order order, List<Book> books) {
    var orderedBooks = orderedBookCreateService.create(order, books);
    return orderedBookWriteService.saveAll(orderedBooks);
  }
}
