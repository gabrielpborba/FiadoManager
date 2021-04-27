package com.fiadomanager.infrastructure.repository;

import com.fiadomanager.models.domain.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {

    List<OrderSheet> findByStatus(Integer status);

}
