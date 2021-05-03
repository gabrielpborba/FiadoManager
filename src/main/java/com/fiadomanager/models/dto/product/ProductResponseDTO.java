package com.fiadomanager.models.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    private List<ProductResponse> products;
    private String totalValue;

}
