package com.kaya.orderapi.validation;

import com.kaya.orderapi.exception.OrderApiException;
import com.kaya.orderapi.service.book.BookReadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookValidatorTest {

  @InjectMocks private BookValidator bookValidator;
  @Mock private BookReadService bookReadService;

  @Test
  public void shouldValidateForExistenceByNameAndWriter() {
    var name = "name";
    var writer = "writer";

    when(bookReadService.existsByNameAndWriter(anyString(), anyString())).thenReturn(false);
    bookValidator.validateForExistenceByNameAndWriter(name, writer);
    verify(bookReadService).existsByNameAndWriter(name, writer);
  }

  @Test(expected = OrderApiException.class)
  public void shouldNotValidateForExistenceByNameAndWriter() {
    var name = "name";
    var writer = "writer";

    when(bookReadService.existsByNameAndWriter(anyString(), anyString())).thenReturn(true);
    bookValidator.validateForExistenceByNameAndWriter(name, writer);
    verify(bookReadService).existsByNameAndWriter(name, writer);
  }
}
