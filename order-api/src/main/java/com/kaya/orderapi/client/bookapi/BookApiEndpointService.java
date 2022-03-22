package com.kaya.orderapi.client.bookapi;

import com.kaya.orderapi.client.bookapi.dto.BookDecreaseStockRequestDTO;
import com.kaya.orderapi.client.bookapi.dto.BookQueryRequest;
import com.kaya.orderapi.client.bookapi.dto.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookApiEndpointService {

  private final BookApiEndpoint endpoint;

  public List<BookResponseDTO> findAllById(Collection<Long> ids) {
    var request = BookQueryRequest.builder().ids(ids).build();
    return Optional.ofNullable(endpoint.search(request).getData()).orElse(new ArrayList<>());
  }

  public void decreaseStocks(Collection<Long> ids) {
    var request = BookDecreaseStockRequestDTO.builder().ids(ids).build();
    endpoint.decreaseStocks(request);
  }

  public Map<Long, BookResponseDTO> findMapById(Collection<Long> ids) {
    var request = BookQueryRequest.builder().ids(ids).build();
    var books = Optional.ofNullable(endpoint.search(request).getData()).orElse(new ArrayList<>());
    return books.stream().collect(Collectors.toMap(BookResponseDTO::getId, Function.identity()));
  }
}
