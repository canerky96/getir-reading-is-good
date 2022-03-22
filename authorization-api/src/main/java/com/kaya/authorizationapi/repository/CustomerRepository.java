package com.kaya.authorizationapi.repository;

import com.kaya.authorizationapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
  Optional<Customer> findByUsername(String username);
  boolean existsByUsername(String username);
}
