package com.kaya.orderapi.service.book;

import com.kaya.orderapi.dto.request.BookCreateRequestDTO;
import com.kaya.orderapi.dto.request.BookUpdateStockRequestDTO;
import com.kaya.orderapi.dto.response.BookCreateResponseDTO;
import com.kaya.orderapi.dto.response.BookResponseDTO;
import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.mapper.BookMapper;
import com.kaya.orderapi.validation.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookMapper bookMapper;
  private final BookValidator bookValidator;
  private final BookCreateService bookCreateService;
  private final BookReadService bookReadService;
  private final BookUpdateService bookUpdateService;

  @Transactional(rollbackFor = Exception.class)
  public BookCreateResponseDTO create(BookCreateRequestDTO request) {
    bookValidator.validateForExistenceByNameAndWriter(request.getName(), request.getWriter());
    var book = bookCreateService.create(request);
    return bookMapper.mapToCreateResponse(book);
  }
  @Transactional(rollbackFor = Exception.class)
  public BookResponseDTO updateStock(Long id, BookUpdateStockRequestDTO request) {
    var book = bookReadService.findById(id);
    book = bookUpdateService.updateStock(book, request.getStock());
    return bookMapper.mapToBookResponse(book);
  }
  @Transactional(rollbackFor = Exception.class)
  public List<Book> findAllById(Set<Long> bookIds) {
    return bookReadService.findAllByIdIn(bookIds);
  }

  @Transactional(rollbackFor = Exception.class)
  public void decreaseStocks(List<Book> books) {
    bookUpdateService.decreaseStock(books);
  }
}
