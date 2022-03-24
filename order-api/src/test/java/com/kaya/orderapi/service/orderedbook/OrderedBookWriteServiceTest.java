package com.kaya.orderapi.service.orderedbook;

import com.kaya.orderapi.entity.OrderedBook;
import com.kaya.orderapi.repository.OrderedBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderedBookWriteServiceTest {

  @InjectMocks private OrderedBookWriteService orderedBookWriteService;
  @Mock private OrderedBookRepository orderedBookRepository;

  @Test
  public void shouldSaveAll() {
    var orderedBook = new OrderedBook();
    var orderedBooks = List.of(orderedBook);

    when(orderedBookRepository.saveAll(anyList())).thenReturn(orderedBooks);

    var result = orderedBookWriteService.saveAll(orderedBooks);
    verify(orderedBookRepository).saveAll(orderedBooks);
    assertEquals(orderedBooks, result);
  }
}
