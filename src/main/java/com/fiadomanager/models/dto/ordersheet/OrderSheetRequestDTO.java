package com.fiadomanager.models.dto.ordersheet;

import lombok.Data;

import java.util.List;

@Data
public class OrderSheetRequestDTO {

    private Long idClient;
    private Long idOrderSheet;
    private List<ProductDTO> products;

}
