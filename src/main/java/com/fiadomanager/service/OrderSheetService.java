package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.OrderSheetRepository;
import com.fiadomanager.models.domain.OrderSheet;
import com.fiadomanager.models.domain.Product;
import com.fiadomanager.models.dto.ordersheet.AllOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderSheetService {

    @Autowired
    private OrderSheetRepository orderSheetRepository;


    public OrderSheetResponseDTO getIdOrderSheet(Long idOrderSheet) {
        try {
            OrderSheetResponseDTO orderSheetResponseDTO = new OrderSheetResponseDTO();
            Optional<OrderSheet> orderSheets = orderSheetRepository.findById(idOrderSheet);
            if (null != orderSheets.get()) {
                orderSheetResponseDTO.setId(orderSheets.get().getId());
                orderSheetResponseDTO.setDateCreate(orderSheets.get().getDateCreate());
                orderSheetResponseDTO.setProducts(orderSheets.get().getProducts());
                orderSheetResponseDTO.setDatePayment(orderSheets.get().getDatePayment());
                Long totalValue = 0L;
                for (Product product : orderSheets.get().getProducts()) {
                    totalValue = totalValue + Long.valueOf(product.getValue());
                }
                orderSheetResponseDTO.setTotalValue(totalValue);
                return orderSheetResponseDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public AllOrderSheetResponseDTO getAllOrderSheet(String status) {
        //TODO FAZER FILTRO DO STATUS
        AllOrderSheetResponseDTO allOrderSheetResponseDTO = new AllOrderSheetResponseDTO();
        List<OrderSheet> orderSheets = orderSheetRepository.findAll();
        if (!orderSheets.isEmpty()) {
            allOrderSheetResponseDTO.setOrderSheets(orderSheets);
            return allOrderSheetResponseDTO;
        } else {
            return null;
        }

    }
}
