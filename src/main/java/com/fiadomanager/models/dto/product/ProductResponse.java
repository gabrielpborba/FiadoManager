package com.fiadomanager.models.dto.product;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String description;
    private Long value;
    private Integer quantity;

    public ProductResponse() {
    }

    public ProductResponse(String s, String s1, long parseLong, int parseInt) {
    }
}
