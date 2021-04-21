package com.fiadomanager.service;

import com.fiadomanager.infrastructure.repository.ProductRepository;
import com.fiadomanager.models.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public String teste() {

        List<Product> product = productRepository.findAll();
        return "teste";
    }
}
