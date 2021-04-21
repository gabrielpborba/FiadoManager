package com.fiadomanager.models.dto.ordersheet;

import com.fiadomanager.models.domain.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderSheetResponseDTO {

    private Long id;
    private LocalDate dateCreate;
    private LocalDate datePayment;
    private List<Product> products;
    private Long totalValue;
    private String status;

}
