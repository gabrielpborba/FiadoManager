package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

    Product findByDescription(String description);

}
