package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.ClientRepository;
import com.fiadomanager.infrastructure.repository.OrderSheetProductRepository;
import com.fiadomanager.infrastructure.repository.OrderSheetRepository;
import com.fiadomanager.infrastructure.repository.ProductRepository;
import com.fiadomanager.models.domain.Client;
import com.fiadomanager.models.domain.OrderSheet;
import com.fiadomanager.models.domain.OrderSheetProduct;
import com.fiadomanager.models.domain.Product;
import com.fiadomanager.models.dto.ordersheet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class OrderSheetService {

    @Autowired
    private OrderSheetRepository orderSheetRepository;

    @Autowired
    private OrderSheetProductRepository orderSheetProductRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;


    public OrderSheetResponseDTO getIdOrderSheet(Long idOrderSheet) {
        try {
            OrderSheetResponseDTO orderSheetResponseDTO = new OrderSheetResponseDTO();
            Optional<OrderSheet> orderSheet = orderSheetRepository.findById(idOrderSheet);
            List<ProductDTO> listProductDTO = new ArrayList<>();
            Long totalValue = 0l;

            if (null != orderSheet.get()) {
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
                    productDTO.setValue(productFind.get().getValue());
                    productDTO.setQuantity(orderSheetProduct.getQuantity());
                    listProductDTO.add(productDTO);
                    totalValue = totalValue + (productFind.get().getValue() * orderSheetProduct.getQuantity());
                }
                Locale localBRL = new Locale("pt", "BR");
                String totalValueFormatted = NumberFormat.getCurrencyInstance(localBRL).format(totalValue);
                orderSheetResponseDTO.setTotalValue(totalValueFormatted);
                orderSheetResponseDTO.setProducts(listProductDTO);
                return orderSheetResponseDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public AllOrderSheetResponseDTO getAllOrderSheet(String status) {
        AllOrderSheetResponseDTO allOrderSheetResponseDTO = new AllOrderSheetResponseDTO();
        List<OrderSheet> orderSheets = orderSheetRepository.findByStatus(Integer.valueOf(status));

        List<OrderSheetDTO> listOrderSheetDTO = new ArrayList<>();


        for (OrderSheet orderSheet : orderSheets) {
            Long totalValue = 0l;
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
                ;
                ProductDTO productDTO = new ProductDTO();
                productDTO.setIdProduct(productFind.get().getId());
                productDTO.setDescription(productFind.get().getDescription());
                productDTO.setValue(productFind.get().getValue());
                productDTO.setQuantity(orderSheetProduct.getQuantity());
                totalValue = totalValue + (productFind.get().getValue() * orderSheetProduct.getQuantity());
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

    }

    public NewOrderSheetResponseDTO newOrderSheet(OrderSheetRequestDTO orderSheetRequestDTO) {
        NewOrderSheetResponseDTO newOrderSheetResponseDTO = new NewOrderSheetResponseDTO();
        Optional<Client> client = clientRepository.findById(orderSheetRequestDTO.getIdClient());

        if (null == orderSheetRequestDTO.getIdOrderSheet() && null != client.get()) {
            OrderSheet orderSheet = new OrderSheet();
            orderSheet.setClient(client.get());
            orderSheet.setDateCreate(LocalDate.now());
            orderSheet.setDatePayment(LocalDate.now());
            orderSheet.setStatus(1);
            OrderSheet newOrderSheet = orderSheetRepository.save(orderSheet);
            if (!orderSheetRequestDTO.getProducts().isEmpty()) {
                for (ProductDTO productDTO : orderSheetRequestDTO.getProducts()) {
                    OrderSheetProduct orderSheetProduct = new OrderSheetProduct();
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
            if (null != client.get() && null != orderSheet.get() && !orderSheetRequestDTO.getProducts().isEmpty()) {
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
                return null;
            }
        }
    }

    public boolean closedOrderSheet(Long idOrderSheet) {
        Optional<OrderSheet> orderSheet = orderSheetRepository.findById(idOrderSheet);

        if (null != orderSheet.get()) {
            orderSheet.get().setStatus(0);
            orderSheet.get().setDatePayment(LocalDate.now());
            orderSheetRepository.save(orderSheet.get());
            return true;
        } else {
            return false;
        }
    }
}
