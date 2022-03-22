package com.kaya.orderapi.controller;

import com.kaya.orderapi.dto.SuccessResponse;
import com.kaya.orderapi.dto.request.BookCreateRequestDTO;
import com.kaya.orderapi.dto.request.BookUpdateStockRequestDTO;
import com.kaya.orderapi.dto.response.BookCreateResponseDTO;
import com.kaya.orderapi.dto.response.BookResponseDTO;
import com.kaya.orderapi.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(BookController.ENDPOINT)
@RequiredArgsConstructor
public class BookController {

  public static final String ENDPOINT = "book";

  private final BookService bookService;

  @PostMapping
  @PreAuthorize("hasAuthority('create_book')")
  public SuccessResponse<BookCreateResponseDTO> create(
      @Valid @RequestBody BookCreateRequestDTO request) {
    var response = bookService.create(request);
    return new SuccessResponse<>(response, HttpStatus.OK.value());
  }

  @PatchMapping("{id}/update-stock")
  @PreAuthorize("hasAuthority('update_book_stock')")
  public SuccessResponse<BookResponseDTO> updateStock(
          @PathVariable("id") Long id, @Valid @RequestBody BookUpdateStockRequestDTO request) {
    var response = bookService.updateStock(id, request);
    return new SuccessResponse<>(response, HttpStatus.OK.value());
  }
}
