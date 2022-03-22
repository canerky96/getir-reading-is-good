package com.kaya.orderapi.service.orderedbook;

import com.kaya.orderapi.entity.OrderedBook;
import com.kaya.orderapi.repository.OrderedBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderedBookWriteService {

  private final OrderedBookRepository orderedBookRepository;

  @Transactional(rollbackFor = Exception.class)
  public List<OrderedBook> saveAll(List<OrderedBook> orderedBooks) {
    return orderedBookRepository.saveAll(orderedBooks);
  }
}
