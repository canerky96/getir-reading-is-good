package com.kaya.orderapi.service.book;

import com.kaya.orderapi.dto.request.BookCreateRequestDTO;
import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCreateService {

  private final BookMapper bookMapper;
  private final BookWriteService bookWriteService;

  public Book create(BookCreateRequestDTO requestDTO) {
    var book = bookMapper.map(requestDTO);
    return bookWriteService.save(book);
  }
}
