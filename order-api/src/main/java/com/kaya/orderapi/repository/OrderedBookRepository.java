package com.kaya.orderapi.repository;

import com.kaya.orderapi.entity.OrderedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedBookRepository extends JpaRepository<OrderedBook, Long> {}
