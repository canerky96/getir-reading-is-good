package com.kaya.orderapi.service.orderedbook;

import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.entity.Order;
import com.kaya.orderapi.entity.OrderedBook;
import com.kaya.orderapi.mapper.OrderMapper;
import com.kaya.orderapi.mapper.OrderedBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderedBookCreateService {

  private final OrderedBookMapper orderedBookMapper;

  public List<OrderedBook> create(Order order, List<Book> books) {
    return books.stream()
        .map(
            book -> {
              var orderedBook = orderedBookMapper.map(book);
              orderedBook.setOrder(order);
              return orderedBook;
            })
        .collect(Collectors.toList());
  }
}
