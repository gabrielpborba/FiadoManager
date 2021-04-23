package com.fiadomanager.models.dto.ordersheet;

import lombok.Data;

import java.util.List;

@Data
public class AllOrderSheetResponseDTO {

    private List<OrderSheetDTO> orderSheets;

}
