package com.fiadomanager.models.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {

    private List<ProductResponse> products;
    private Long totalValue;

}
