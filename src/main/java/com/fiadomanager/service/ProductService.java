package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.ProductRepository;
import com.fiadomanager.models.domain.Product;
import com.fiadomanager.models.dto.product.NewProductRequestDTO;
import com.fiadomanager.models.dto.product.NewProductResponseDTO;
import com.fiadomanager.models.dto.product.ProductResponse;
import com.fiadomanager.models.dto.product.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public NewProductResponseDTO newProduct(NewProductRequestDTO newProductRequestDTO) throws Exception {

        NewProductResponseDTO newProductResponseDTO = new NewProductResponseDTO();
        if (null != newProductRequestDTO.getIdProduct()) {
            Optional<Product> product = productRepository.findById(newProductRequestDTO.getIdProduct());
            if (null != product.get()) {
                product.get().setValue(newProductRequestDTO.getValue());
                product.get().setId(newProductRequestDTO.getIdProduct());
                product.get().setDescription(newProductRequestDTO.getDescription());
                productRepository.save(product.get());
                newProductResponseDTO.setIdProduct(product.get().getId());
                return newProductResponseDTO;
            } else {
                return null;
            }
        } else {
            Product product = new Product();
            Product findProduct = productRepository.findByDescription(newProductRequestDTO.getDescription());
            if (null == findProduct) {
                product.setDescription(newProductRequestDTO.getDescription());
                product.setValue(newProductRequestDTO.getValue());
                productRepository.save(product);
                newProductResponseDTO.setIdProduct(product.getId());
                return newProductResponseDTO;
            }
        }
        return null;
    }


    public ProductResponseDTO getAllProducts() {

        List<Product> allProducts = productRepository.findAll();

        if (!allProducts.isEmpty()) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            List<ProductResponse> listProductResponse = new ArrayList<>();

            for (Product product : allProducts) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(product.getId());
                productResponse.setDescription(product.getDescription());
                productResponse.setValue(product.getValue());
                listProductResponse.add(productResponse);
            }

            productResponseDTO.setProducts(listProductResponse);
            return productResponseDTO;
        } else {
            return null;
        }
    }
}