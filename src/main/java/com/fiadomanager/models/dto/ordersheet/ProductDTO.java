package com.fiadomanager.models.dto.ordersheet;

import lombok.Data;

@Data
public class ProductDTO {

    private Long idProduct;
    private String description;
    private String value;
    private Long quantity;
    private Long idOrderSheetProduct;

}
