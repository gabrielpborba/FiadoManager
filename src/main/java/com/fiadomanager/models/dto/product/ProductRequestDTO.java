package com.fiadomanager.models.dto.product;

import lombok.Data;

@Data
public class ProductRequestDTO {

    private Long id;
    private String description;
    private Long value;
    private String quantity;

}
