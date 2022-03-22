package com.kaya.orderapi.repository;

import com.kaya.orderapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  boolean existsByNameAndWriter(String name, String writer);

  List<Book> findAllByIdIn(Collection<Long> ids);
}
