package com.fiadomanager.service;

import com.fiadomanager.models.dto.ordersheet.AllOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.NewOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetRequestDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetResponseDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;

public interface OrderSheetService {

    OrderSheetResponseDTO getIdOrderSheet(Long idOrderSheet) throws FiadoManagerCustomException;

    OrderSheetResponseDTO getOrderSheetByIdClient(Long idClient) throws FiadoManagerCustomException;

    AllOrderSheetResponseDTO getAllOrderSheet(String status) throws FiadoManagerCustomException;

    NewOrderSheetResponseDTO newOrderSheet(OrderSheetRequestDTO orderSheetRequestDTO) throws FiadoManagerCustomException;

    boolean closedOrderSheet(Long idOrderSheet) throws FiadoManagerCustomException;

    boolean deleteOrderSheet(Long idOrderSheet) throws FiadoManagerCustomException;
}
