package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.OrderSheetProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderSheetProductRepository extends MongoRepository<OrderSheetProduct, Long> {


    List<OrderSheetProduct> findByIdOrderSheet(Long idOrderSheet);

}
