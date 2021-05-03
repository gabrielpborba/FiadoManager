package com.fiadomanager.service;

import com.fiadomanager.models.dto.product.NewProductRequestDTO;
import com.fiadomanager.models.dto.product.NewProductResponseDTO;
import com.fiadomanager.models.dto.product.ProductResponseDTO;
import com.fiadomanager.models.exception.FiadoManagerCustomException;

public interface ProductService {

    NewProductResponseDTO newProduct(NewProductRequestDTO newProductRequestDTO) throws FiadoManagerCustomException;

    ProductResponseDTO getAllProducts() throws FiadoManagerCustomException;

    boolean deleteProductFromAOrderSheet(Long idOrderSheetProduct) throws FiadoManagerCustomException;

}
