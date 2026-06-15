package com.ecom.product.service;

import com.ecom.product.apiresponse.APIResponse;
import com.ecom.product.dtos.ProductDTO;
import com.ecom.product.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO getProducts(Integer page, Integer size, String sortBy, String sort) {
        // Implement the logic to fetch products from the repository, apply pagination and sorting
        // For now, we will return a placeholder response

        return new ProductDTO();
    }
}
