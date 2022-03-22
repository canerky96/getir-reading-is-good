package com.kaya.orderapi.client.bookapi;

import com.kaya.orderapi.client.bookapi.dto.BookDecreaseStockRequestDTO;
import com.kaya.orderapi.client.bookapi.dto.BookQueryRequest;
import com.kaya.orderapi.client.bookapi.dto.BookResponseDTO;
import com.kaya.orderapi.dto.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "book-api", url = "${feign.book-api.url}")
public interface BookApiEndpoint {

  @PostMapping("search")
  SuccessResponse<List<BookResponseDTO>> search(@Valid @RequestBody BookQueryRequest request);

  @PostMapping("decrease-stocks")
  void decreaseStocks(@Valid @RequestBody BookDecreaseStockRequestDTO requestDTO);
}
