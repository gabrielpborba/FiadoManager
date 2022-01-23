package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.OrderSheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSheetRepository extends MongoRepository<OrderSheet, Long> {

    List<OrderSheet> findByStatus(Integer status);

}
