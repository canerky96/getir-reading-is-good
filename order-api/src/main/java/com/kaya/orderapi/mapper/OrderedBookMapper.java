package com.kaya.orderapi.mapper;

import com.kaya.orderapi.dto.response.OrderedBookResponseDTO;
import com.kaya.orderapi.entity.Book;
import com.kaya.orderapi.entity.OrderedBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderedBookMapper {

  OrderedBook map(Book book);

  OrderedBookResponseDTO map(OrderedBook book);
}
