package com.fiadomanager.models.dto.ordersheet;

import com.fiadomanager.models.domain.Client;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderSheetDTO {

    private Long id;
    private LocalDate dateCreate;
    private LocalDate datePayment;
    private Client client;
    private List<ProductDTO> products;
    private Integer status;
    private String totalValue;

}
