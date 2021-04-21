package com.fiadomanager.models.dto.ordersheet;

import com.fiadomanager.models.domain.OrderSheet;
import lombok.Data;

import java.util.List;

@Data
public class AllOrderSheetResponseDTO {

    private List<OrderSheet> orderSheets;

}
