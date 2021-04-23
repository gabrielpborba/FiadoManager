package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.ProductRepository;
import com.fiadomanager.models.domain.Product;
import com.fiadomanager.models.dto.product.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public boolean newProduct(ProductRequestDTO productRequestDTO) throws Exception {

        try {
            Product product = new Product();
            Product findProduct = productRepository.findByDescription(productRequestDTO.getDescription());

            if (null == findProduct) {
                product.setDescription(productRequestDTO.getDescription());
                product.setValue(productRequestDTO.getValue());
                productRepository.saveAndFlush(product);
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new Exception("Erro ao salvar novo Produto");

        }

        return false;
    }
}