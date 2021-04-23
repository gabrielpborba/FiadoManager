package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.OrderSheetProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSheetProductRepository extends JpaRepository<OrderSheetProduct, Long> {


    List<OrderSheetProduct> findByIdOrderSheet(Long idOrderSheet);

}
