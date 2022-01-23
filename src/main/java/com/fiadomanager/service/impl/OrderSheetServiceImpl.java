package com.fiadomanager.service.impl;

import com.fiadomanager.infrastructure.repository.ClientRepository;
import com.fiadomanager.infrastructure.repository.OrderSheetProductRepository;
import com.fiadomanager.infrastructure.repository.OrderSheetRepository;
import com.fiadomanager.infrastructure.repository.ProductRepository;
import com.fiadomanager.models.domain.Client;
import com.fiadomanager.models.domain.OrderSheet;
import com.fiadomanager.models.domain.OrderSheetProduct;
import com.fiadomanager.models.domain.Product;
import com.fiadomanager.models.dto.ordersheet.*;
import com.fiadomanager.models.exception.FiadoManagerCustomException;
import com.fiadomanager.service.OrderSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class OrderSheetServiceImpl implements OrderSheetService {

    @Autowired
    private OrderSheetRepository orderSheetRepository;

    @Autowired
    private OrderSheetProductRepository orderSheetProductRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NextSequenceService nextSequenceService;


    public OrderSheetResponseDTO getIdOrderSheet(Long idOrderSheet) throws FiadoManagerCustomException {
        try {
            OrderSheetResponseDTO orderSheetResponseDTO = new OrderSheetResponseDTO();
            Optional<OrderSheet> orderSheet = orderSheetRepository.findById(idOrderSheet);
            List<ProductDTO> listProductDTO = new ArrayList<>();
            double totalValue = 0l;

            if (! orderSheet.isEmpty()) {
                List<OrderSheetProduct> listOrderSheetProducts = orderSheetProductRepository.findByIdOrderSheet(idOrderSheet);
                orderSheetResponseDTO.setId(orderSheet.get().getId());
                orderSheetResponseDTO.setDateCreate(orderSheet.get().getDateCreate());
                orderSheetResponseDTO.setDatePayment(orderSheet.get().getDatePayment());
                orderSheetResponseDTO.setClient(orderSheet.get().getClient());
                orderSheetResponseDTO.setStatus(orderSheet.get().getStatus());
                for (OrderSheetProduct orderSheetProduct : listOrderSheetProducts) {
                    Optional<Product> productFind = orderSheet.get().getProducts().stream().filter(product -> product.getId().equals(orderSheetProduct.getIdProduct())).findAny();
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setIdProduct(productFind.get().getId());
                    productDTO.setDescription(productFind.get().getDescription());
                    double totalValueOfProduct = productFind.get().getValue() * orderSheetProduct.getQuantity();
                    Locale localBRL = new Locale("pt", "BR");
                    String valueFormatted = NumberFormat.getCurrencyInstance(localBRL).format(totalValueOfProduct);
                    productDTO.setValue(valueFormatted.replace(" ", ""));
                    productDTO.setQuantity(orderSheetProduct.getQuantity());
                    productDTO.setIdOrderSheetProduct(orderSheetProduct.getId());
                    listProductDTO.add(productDTO);
                    totalValue = totalValue + (productFind.get().getValue() * orderSheetProduct.getQuantity());
                }
                Locale localBRL = new Locale("pt", "BR");
                String totalValueFormatted = NumberFormat.getCurrencyInstance(localBRL).format(totalValue);
                orderSheetResponseDTO.setTotalValue(totalValueFormatted);
                orderSheetResponseDTO.setProducts(listProductDTO);
                return orderSheetResponseDTO;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Nenhuma comanda encontrada");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public OrderSheetResponseDTO getOrderSheetByIdClient(Long idClient) throws FiadoManagerCustomException {

        try {
            OrderSheetResponseDTO orderSheetResponseDTO = new OrderSheetResponseDTO();
            AllOrderSheetResponseDTO listOrderSheet = getAllOrderSheet("1");

            for (OrderSheetDTO orderSheet : listOrderSheet.getOrderSheets()) {
                if (orderSheet.getClient().getId().equals(idClient)) {
                    orderSheetResponseDTO.setTotalValue(orderSheet.getTotalValue());
                    orderSheetResponseDTO.setClient(orderSheet.getClient());
                    orderSheetResponseDTO.setProducts(orderSheet.getProducts());
                    orderSheetResponseDTO.setDatePayment(orderSheet.getDatePayment());
                    orderSheetResponseDTO.setId(orderSheet.getId());
                    orderSheetResponseDTO.setDateCreate(orderSheet.getDateCreate());
                    orderSheetResponseDTO.setStatus(orderSheet.getStatus());
                    break;
                }

            }

            return orderSheetResponseDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    public AllOrderSheetResponseDTO getAllOrderSheet(String status) throws FiadoManagerCustomException {

        try {
            AllOrderSheetResponseDTO allOrderSheetResponseDTO = new AllOrderSheetResponseDTO();
            List<OrderSheet> orderSheets = orderSheetRepository.findByStatus(Integer.valueOf(status));

            List<OrderSheetDTO> listOrderSheetDTO = new ArrayList<>();


            if (!orderSheets.isEmpty()) {

                for (OrderSheet orderSheet : orderSheets) {
                    double totalValue = 0l;
                    List<ProductDTO> listProductDTO = new ArrayList<>();
                    List<OrderSheetProduct> listOrderSheetProducts = orderSheetProductRepository.findByIdOrderSheet(orderSheet.getId());

                    OrderSheetDTO orderSheetDTO = new OrderSheetDTO();
                    orderSheetDTO.setId(orderSheet.getId());
                    orderSheetDTO.setDateCreate(orderSheet.getDateCreate());
                    orderSheetDTO.setDatePayment(orderSheet.getDatePayment());
                    orderSheetDTO.setClient(orderSheet.getClient());
                    orderSheetDTO.setStatus(orderSheet.getStatus());


                    for (OrderSheetProduct orderSheetProduct : listOrderSheetProducts) {
                        Optional<Product> productFind = orderSheet.getProducts().stream().filter(product -> product.getId().equals(orderSheetProduct.getIdProduct())).findAny();
                        ProductDTO productDTO = new ProductDTO();
                        productDTO.setIdProduct(productFind.get().getId());
                        productDTO.setDescription(productFind.get().getDescription());
                        double totalValueOfProduct = productFind.get().getValue() * orderSheetProduct.getQuantity();
                        Locale localBRL = new Locale("pt", "BR");
                        String valueFormatted = NumberFormat.getCurrencyInstance(localBRL).format(totalValueOfProduct);
                        productDTO.setValue(valueFormatted.replace(" ", ""));
                        productDTO.setQuantity(orderSheetProduct.getQuantity());
                        totalValue = totalValue + (productFind.get().getValue() * orderSheetProduct.getQuantity());
                        productDTO.setIdOrderSheetProduct(orderSheetProduct.getId());
                        listProductDTO.add(productDTO);
                    }
                    Locale localBRL = new Locale("pt", "BR");
                    String totalValueFormatted = NumberFormat.getCurrencyInstance(localBRL).format(totalValue);
                    orderSheetDTO.setTotalValue(totalValueFormatted);
                    orderSheetDTO.setProducts(listProductDTO);
                    listOrderSheetDTO.add(orderSheetDTO);
                }
                allOrderSheetResponseDTO.setOrderSheets(listOrderSheetDTO);
                return allOrderSheetResponseDTO;
            } else {
                allOrderSheetResponseDTO.setOrderSheets(new ArrayList<>());
                return allOrderSheetResponseDTO;
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public NewOrderSheetResponseDTO newOrderSheet(OrderSheetRequestDTO orderSheetRequestDTO) throws FiadoManagerCustomException {

        try {
            NewOrderSheetResponseDTO newOrderSheetResponseDTO = new NewOrderSheetResponseDTO();
            Optional<Client> client = clientRepository.findById(orderSheetRequestDTO.getIdClient());

            if (null == orderSheetRequestDTO.getIdOrderSheet() && !client.isEmpty()) {
                OrderSheet orderSheet = new OrderSheet();
                orderSheet.setId(nextSequenceService.getNextSequenceOrderSheet("customSequences_orderSheet"));
                orderSheet.setClient(client.get());
                orderSheet.setDateCreate(LocalDate.now());
                orderSheet.setStatus(1);
                OrderSheet newOrderSheet = orderSheetRepository.save(orderSheet);
                if (!orderSheetRequestDTO.getProducts().isEmpty()) {
                    for (ProductDTO productDTO : orderSheetRequestDTO.getProducts()) {
                        OrderSheetProduct orderSheetProduct = new OrderSheetProduct();
                        orderSheetProduct.setId(nextSequenceService.getNextSequenceOrderSheetProduct("customSequences_orderSheetProduct"));
                        orderSheetProduct.setIdOrderSheet(newOrderSheet.getId());
                        orderSheetProduct.setIdProduct(productDTO.getIdProduct());
                        orderSheetProduct.setQuantity(productDTO.getQuantity());
                        orderSheetProductRepository.save(orderSheetProduct);
                    }
                    newOrderSheetResponseDTO.setIdOrderSheet(newOrderSheet.getId());
                    return newOrderSheetResponseDTO;
                }
                newOrderSheetResponseDTO.setIdOrderSheet(newOrderSheet.getId());
                return newOrderSheetResponseDTO;
            } else {
                Optional<OrderSheet> orderSheet = orderSheetRepository.findById(orderSheetRequestDTO.getIdOrderSheet());
                if (!client.isEmpty() && !orderSheet.isEmpty() && !orderSheetRequestDTO.getProducts().isEmpty()) {
                    for (ProductDTO productDTO : orderSheetRequestDTO.getProducts()) {
                        OrderSheetProduct orderSheetProduct = new OrderSheetProduct();
                        orderSheetProduct.setIdOrderSheet(orderSheet.get().getId());
                        orderSheetProduct.setIdProduct(productDTO.getIdProduct());
                        orderSheetProduct.setQuantity(productDTO.getQuantity());
                        orderSheetProductRepository.save(orderSheetProduct);
                    }
                    newOrderSheetResponseDTO.setIdOrderSheet(orderSheet.get().getId());
                    return newOrderSheetResponseDTO;
                } else {
                    throw new FiadoManagerCustomException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro ao criar comanda");
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean closedOrderSheet(Long idOrderSheet) throws FiadoManagerCustomException {

        try {
            Optional<OrderSheet> orderSheet = orderSheetRepository.findById(idOrderSheet);

            if (!orderSheet.isEmpty()) {
                orderSheet.get().setStatus(0);
                orderSheet.get().setDatePayment(LocalDate.now());
                orderSheetRepository.save(orderSheet.get());
                return true;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Comanda não encontrada");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deleteOrderSheet(Long idOrderSheet) throws FiadoManagerCustomException {

        try {
            Optional<OrderSheet> orderSheet = orderSheetRepository.findById(idOrderSheet);

            if (!orderSheet.isEmpty()) {
                List<OrderSheetProduct> orderSheetProduct = orderSheetProductRepository.findByIdOrderSheet(orderSheet.get().getId());
                for (OrderSheetProduct orderSheetProduct1 : orderSheetProduct) {
                    orderSheetProductRepository.delete(orderSheetProduct1);
                }
                orderSheetRepository.delete(orderSheet.get());
                return true;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Comanda não encontrada");
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
