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
import com.fiadomanager.models.exception.FiadoManagerCustomException;
import com.fiadomanager.service.impl.ClientServiceImpl;
import com.fiadomanager.service.impl.OrderSheetServiceImpl;
import com.fiadomanager.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FiadoController {

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @Autowired
    private OrderSheetServiceImpl orderSheetServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping(path = "/newClient")
    private ResponseEntity<Boolean> newClient(@RequestBody NewClientRequestDTO newClientRequestDTO) throws FiadoManagerCustomException {
        Boolean newClient = clientServiceImpl.newClient(newClientRequestDTO);
        return ResponseEntity.status(newClient ? HttpStatus.OK : HttpStatus.CONFLICT).body(newClient);
    }

    @GetMapping(path = "/getClients")
    private ResponseEntity<ClientResponseDTO> getAllClients() throws FiadoManagerCustomException {
        ClientResponseDTO clientResponseDTO = clientServiceImpl.getClients();
        return ResponseEntity.status(null != clientResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(clientResponseDTO);
    }

    @GetMapping(path = "/getOrderSheets/id/{id}")
    private ResponseEntity<OrderSheetResponseDTO> getOrderSheets(@PathVariable("id") Long idOrderSheet) throws FiadoManagerCustomException {
        OrderSheetResponseDTO orderSheetResponseDTO = orderSheetServiceImpl.getIdOrderSheet(idOrderSheet);
        return ResponseEntity.status(null != orderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(orderSheetResponseDTO);
    }

    @GetMapping(path = "/getOrderSheets/idClient/{idClient}")
    private ResponseEntity<OrderSheetResponseDTO> getOrderSheetsByIdClient(@PathVariable("idClient") Long idClient) throws FiadoManagerCustomException {
        OrderSheetResponseDTO orderSheetResponseDTO = orderSheetServiceImpl.getOrderSheetByIdClient(idClient);
        return ResponseEntity.status(null != orderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(orderSheetResponseDTO);
    }

    @GetMapping(path = "/getOrderSheets/status/{status}")
    private ResponseEntity<AllOrderSheetResponseDTO> getOrderSheets(@PathVariable("status") String status) throws FiadoManagerCustomException {
        AllOrderSheetResponseDTO allOrderSheetResponseDTO = orderSheetServiceImpl.getAllOrderSheet(status);
        return ResponseEntity.status(null != allOrderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(allOrderSheetResponseDTO);
    }

    @PostMapping(path = "/newProduct")
    private ResponseEntity<NewProductResponseDTO> newProduct(@RequestBody NewProductRequestDTO newProductRequestDTO) throws FiadoManagerCustomException {
        NewProductResponseDTO newProduct = productServiceImpl.newProduct(newProductRequestDTO);
        return ResponseEntity.status(null != newProduct ? HttpStatus.OK : HttpStatus.CONFLICT).body(newProduct);
    }

    @GetMapping(path = "/closedOrderSheets/{idOrderSheet}")
    private ResponseEntity<Boolean> closedOrderSheet(@PathVariable("idOrderSheet") Long idOrderSheet) throws FiadoManagerCustomException {
        Boolean closedOrderSheet = orderSheetServiceImpl.closedOrderSheet(idOrderSheet);
        return ResponseEntity.status(closedOrderSheet ? HttpStatus.OK : HttpStatus.CONFLICT).body(closedOrderSheet);
    }

    @PostMapping(path = "/newOrderSheet")
    private ResponseEntity<NewOrderSheetResponseDTO> newOrderSheet(@RequestBody OrderSheetRequestDTO orderSheetRequestDTO) throws FiadoManagerCustomException {
        NewOrderSheetResponseDTO newOrderSheetResponseDTO = orderSheetServiceImpl.newOrderSheet(orderSheetRequestDTO);
        return ResponseEntity.status(null != newOrderSheetResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(newOrderSheetResponseDTO);
    }

    @GetMapping(path = "/getAllProducts")
    private ResponseEntity<ProductResponseDTO> getAllProducts() throws FiadoManagerCustomException {
        ProductResponseDTO productResponseDTO = productServiceImpl.getAllProducts();
        return ResponseEntity.status(null != productResponseDTO ? HttpStatus.OK : HttpStatus.CONFLICT).body(productResponseDTO);
    }


    @GetMapping(path = "/disableClient/{idClient}")
    private ResponseEntity<Boolean> disableClient(@PathVariable("idClient") Long idClient) throws FiadoManagerCustomException {
        Boolean deleteClient = clientServiceImpl.disableClient(idClient);
        return ResponseEntity.status(deleteClient ? HttpStatus.OK : HttpStatus.CONFLICT).body(deleteClient);
    }

    @DeleteMapping(path = "/deleteOrderSheet/{idOrderSheet}")
    private ResponseEntity<Boolean> deleteOrderSheet(@PathVariable("idOrderSheet") Long idOrderSheet) throws FiadoManagerCustomException {
        Boolean deleteClient = orderSheetServiceImpl.deleteOrderSheet(idOrderSheet);
        return ResponseEntity.status(deleteClient ? HttpStatus.OK : HttpStatus.CONFLICT).body(deleteClient);
    }


    @DeleteMapping(path = "/deleteProductFromAOrderSheet/{idOrderSheetProduct}")
    private ResponseEntity<Boolean> deleteProductFromAOrderSheet(@PathVariable("idOrderSheetProduct") Long idOrderSheetProduct) throws FiadoManagerCustomException {
        Boolean deleteProductFromAOrderSheet = productServiceImpl.deleteProductFromAOrderSheet(idOrderSheetProduct);
        return ResponseEntity.status(deleteProductFromAOrderSheet ? HttpStatus.OK : HttpStatus.CONFLICT).body(deleteProductFromAOrderSheet);
    }
}
