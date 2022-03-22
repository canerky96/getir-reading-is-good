package com.kaya.orderapi.mapper;

import com.kaya.orderapi.dto.request.BookCreateRequestDTO;
import com.kaya.orderapi.dto.response.BookCreateResponseDTO;
import com.kaya.orderapi.dto.response.BookResponseDTO;
import com.kaya.orderapi.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
  BookCreateResponseDTO mapToCreateResponse(Book book);

  Book map(BookCreateRequestDTO bookCreateRequestDTO);

  BookResponseDTO mapToBookResponse(Book book);
}
