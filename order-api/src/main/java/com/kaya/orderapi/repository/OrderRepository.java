package com.kaya.orderapi.repository;

import com.kaya.orderapi.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Page<Order> findAllByUsername(String username, Pageable pageable);
}
