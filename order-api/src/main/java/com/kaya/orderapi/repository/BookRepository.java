package com.kaya.orderapi.repository;

import com.kaya.orderapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
  Optional<Book> findById(Long id);

  boolean existsByNameAndWriter(String name, String writer);

  List<Book> findAllByIdIn(Collection<Long> ids);
}
