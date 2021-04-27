package com.fiadomanager.controller;

import com.fiadomanager.models.dto.clients.ClientResponseDTO;
import com.fiadomanager.models.dto.clients.NewClientRequestDTO;
import com.fiadomanager.models.dto.ordersheet.AllOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.NewOrderSheetResponseDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetRequestDTO;
import com.fiadomanager.models.dto.ordersheet.OrderSheetResponseDTO;
import com.fiadomanager.models.dto.product.NewProductRequestDTO;
import com.fiadomanager.models.dto.product.NewProductResponseDTO;
import com.fiadomanager.models.dto.product.ProductResponseDTO;
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
    private ResponseEntity<Boolean> newClient(@RequestBody NewClientRequestDTO newClientRequestDTO) throws Exception {
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

    @GetMapping(path = "/getOrderSheets/idClient/{idClient}")
    private ResponseEntity<OrderSheetResponseDTO> getOrderSheetsByIdClient(@PathVariable("idClient") Long idClient) throws Exception {
        OrderSheetResponseDTO orderSheetResponseDTO = orderSheetService.getOrderSheetByIdClient(idClient);
        return ResponseEntity.status(null != orderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(orderSheetResponseDTO);
    }

    @GetMapping(path = "/getOrderSheets/status/{status}")
    private ResponseEntity<AllOrderSheetResponseDTO> getOrderSheets(@PathVariable("status") String status) {
        AllOrderSheetResponseDTO allOrderSheetResponseDTO = orderSheetService.getAllOrderSheet(status);
        return ResponseEntity.status(null != allOrderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(allOrderSheetResponseDTO);
    }

    @PostMapping(path = "/newProduct")
    private ResponseEntity<NewProductResponseDTO> newProduct(@RequestBody NewProductRequestDTO newProductRequestDTO) throws Exception {
        NewProductResponseDTO newProduct = productService.newProduct(newProductRequestDTO);
        return ResponseEntity.status(null != newProduct ? HttpStatus.OK : HttpStatus.CONFLICT).body(newProduct);
    }

    @GetMapping(path = "/closedOrderSheets/{idOrderSheet}")
    private ResponseEntity<Boolean> closedOrderSheet(@PathVariable("idOrderSheet") Long idOrderSheet) {
        Boolean closedOrderSheet = orderSheetService.closedOrderSheet(idOrderSheet);
        return ResponseEntity.status(closedOrderSheet ? HttpStatus.OK : HttpStatus.CONFLICT).body(closedOrderSheet);
    }

    @PostMapping(path = "/newOrderSheet")
    private ResponseEntity<NewOrderSheetResponseDTO> newOrderSheet(@RequestBody OrderSheetRequestDTO orderSheetRequestDTO) {
        NewOrderSheetResponseDTO newOrderSheetResponseDTO = orderSheetService.newOrderSheet(orderSheetRequestDTO);
        return ResponseEntity.status(null != newOrderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(newOrderSheetResponseDTO);
    }

    @GetMapping(path = "/getAllProducts")
    private ResponseEntity<ProductResponseDTO> getAllProducts() {
        ProductResponseDTO productResponseDTO = productService.getAllProducts();
        return ResponseEntity.status(null != productResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(productResponseDTO);
    }


}
