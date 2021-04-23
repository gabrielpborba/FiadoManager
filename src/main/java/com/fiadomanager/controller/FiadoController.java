package com.fiadomanager.controller;

import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import com.fiadomanager.models.dto.ordersheet.AllOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.NewOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetRequestDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetResponseDTO;
import com.fiadomanager.models.dto.product.ProductRequestDTO;
import com.fiadomanager.service.ClientService;
import com.fiadomanager.service.OrderSheetService;
import com.fiadomanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FiadoController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderSheetService orderSheetService;

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/newClient")
    private ResponseEntity<Boolean> newClients(@RequestBody NewClientRequestDTO newClientRequestDTO) throws Exception {
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

    @PostMapping(path = "/newProduct")
    private ResponseEntity<Boolean> newProduct(@RequestBody ProductRequestDTO productRequestDTO) throws Exception {
        Boolean newClient = productService.newProduct(productRequestDTO);
        return ResponseEntity.status(newClient ? HttpStatus.OK : HttpStatus.CONFLICT).body(newClient);
    }

    @GetMapping(path = "/closedOrderSheets/{idOrderSheet}")
    private ResponseEntity<Boolean> closedOrderSheets(@PathVariable("idOrderSheet") Long idOrderSheet) throws Exception {
        Boolean newClient = orderSheetService.closedOrderSheet(idOrderSheet);
        return ResponseEntity.status(newClient ? HttpStatus.OK : HttpStatus.CONFLICT).body(newClient);
    }

    @PostMapping(path = "/newOrderSheet")
    private ResponseEntity<NewOrderSheetResponseDTO> newOrderSheet(@RequestBody OrderSheetRequestDTO orderSheetRequestDTO) throws Exception {
        NewOrderSheetResponseDTO newClient = orderSheetService.newOrderSheet(orderSheetRequestDTO);
        return ResponseEntity.status(null != newClient ? HttpStatus.OK : HttpStatus.CONFLICT).body(newClient);
    }


}
