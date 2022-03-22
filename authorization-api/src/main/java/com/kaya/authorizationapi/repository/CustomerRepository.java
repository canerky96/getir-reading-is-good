package com.kaya.authorizationapi.repository;

import com.kaya.authorizationapi.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
  Optional<Customer> findByUsername(String username);

  boolean existsByUsername(String username);
}
