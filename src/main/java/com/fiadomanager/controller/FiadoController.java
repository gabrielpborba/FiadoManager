package com.fiadomanager.controller;

import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import com.fiadomanager.models.dto.ordersheet.AllOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetResponseDTO;
import com.fiadomanager.service.ClientService;
import com.fiadomanager.service.OrderSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FiadoController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderSheetService orderSheetService;

    @PostMapping(path = "/newClient")
    private ResponseEntity<Boolean> newClients(NewClientRequestDTO newClientRequestDTO) throws Exception {
        Boolean newClient = clientService.newClient(newClientRequestDTO);
        return ResponseEntity.status(newClient ? HttpStatus.OK : HttpStatus.CONFLICT).body(newClient);
    }

    @GetMapping(path = "/getClients")
    private ResponseEntity<ClientResponseDTO> getClients() {
        ClientResponseDTO clientResponseDTO = clientService.getClients();
        return ResponseEntity.status(null != clientResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(clientResponseDTO);
    }

    @GetMapping(path = "/getOrderSheets/id/{id}")
    private ResponseEntity<OrderSheetResponseDTO> getOrderSheets(@PathVariable("id") Long idOrderSheet) throws Exception {
        OrderSheetResponseDTO orderSheetResponseDTO = orderSheetService.getIdOrderSheet(idOrderSheet);
        return ResponseEntity.status(null != orderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(orderSheetResponseDTO);
    }

    @GetMapping(path = "/getOrderSheets/status/{status}")
    private ResponseEntity<AllOrderSheetResponseDTO> getOrderSheets(@PathVariable("status") String status) {
        AllOrderSheetResponseDTO allOrderSheetResponseDTO = orderSheetService.getAllOrderSheet(status);
        return ResponseEntity.status(null != allOrderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(allOrderSheetResponseDTO);
    }

}
