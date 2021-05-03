package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.OrderSheetProductRepository;
import com.fiadomanager.infrastructure.repository.ProductRepository;
import com.fiadomanager.models.domain.OrderSheetProduct;
import com.fiadomanager.models.domain.Product;
import com.fiadomanager.models.dto.product.NewProductRequestDTO;
import com.fiadomanager.models.dto.product.NewProductResponseDTO;
import com.fiadomanager.models.dto.product.ProductResponse;
import com.fiadomanager.models.dto.product.ProductResponseDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderSheetProductRepository orderSheetProductRepository;

    public NewProductResponseDTO newProduct(NewProductRequestDTO newProductRequestDTO) throws FiadoManagerCustomException {

        try {

            NewProductResponseDTO newProductResponseDTO = new NewProductResponseDTO();

            if (newProductRequestDTO.getIdProduct() != null) {
                Optional<Product> product = productRepository.findById(newProductRequestDTO.getIdProduct());
                if (!product.isEmpty()) {
                     Double valueToDouble = Double.parseDouble(newProductRequestDTO.getValue());
                    product.get().setValue(valueToDouble);
                    product.get().setId(newProductRequestDTO.getIdProduct());
                    product.get().setDescription(StringUtils.capitalize(newProductRequestDTO.getDescription()));
                    productRepository.save(product.get());
                    newProductResponseDTO.setIdProduct(product.get().getId());
                    return newProductResponseDTO;
                } else {
                    throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Produto não encontrado");
                }
            } else {
                Product newProduct = new Product();
                Product findProduct = productRepository.findByDescription(StringUtils.capitalize(newProductRequestDTO.getDescription()));
                if (null == findProduct) {
                    if (newProductRequestDTO.getValue().contains(",")) {
                        newProductRequestDTO.setValue(newProductRequestDTO.getValue().replace(",", "."));
                    }
                    Double valueToDouble = Double.parseDouble(newProductRequestDTO.getValue());
                    newProduct.setDescription(StringUtils.capitalize(newProductRequestDTO.getDescription()));
                    newProduct.setValue(valueToDouble);
                    productRepository.save(newProduct);
                    newProductResponseDTO.setIdProduct(newProduct.getId());
                    return newProductResponseDTO;
                } else {
                    throw new FiadoManagerCustomException(HttpStatus.CONFLICT, "Já existe um produto com esse nome");
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ProductResponseDTO getAllProducts() throws FiadoManagerCustomException {

        try {

            List<Product> allProducts = productRepository.findAll();

            if (!allProducts.isEmpty()) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO();
                List<ProductResponse> listProductResponse = new ArrayList<>();

                for (Product product : allProducts) {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setDescription(product.getDescription());
                    Locale localBRL = new Locale("pt", "BR");
                    String valueFormatted = NumberFormat.getCurrencyInstance(localBRL).format(product.getValue());
                    productResponse.setValue(valueFormatted);
                    listProductResponse.add(productResponse);
                }

                listProductResponse.sort(Comparator.comparing(ProductResponse::getDescription));
                productResponseDTO.setProducts(listProductResponse);
                return productResponseDTO;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Nenhum produto cadastrado");
            }

        } catch (Exception e) {
            throw e;
        }
    }


    public boolean deleteProductFromAOrderSheet(Long idOrderSheetProduct) throws FiadoManagerCustomException {

        try {

            Optional<OrderSheetProduct> findOrderSheetProduct = orderSheetProductRepository.findById(idOrderSheetProduct);

            if (!findOrderSheetProduct.isEmpty()) {
                OrderSheetProduct orderSheetProduct = new OrderSheetProduct();
                orderSheetProduct.setId(findOrderSheetProduct.get().getId());
                orderSheetProductRepository.delete(orderSheetProduct);
                return true;
            } else {
                throw new FiadoManagerCustomException(HttpStatus.NOT_FOUND, "Produto não encontrado na comanda");
            }
        } catch (Exception e) {
            throw e;
        }
    }


}