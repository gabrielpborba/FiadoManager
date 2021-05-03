package com.fiadomanager.models.dto.product;

import lombok.Data;

@Data
public class NewProductRequestDTO {

    private Long idProduct;
    private String description;
    private String value;

}
