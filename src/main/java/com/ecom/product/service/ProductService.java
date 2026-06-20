package com.ecom.product.service;

import com.ecom.product.dtos.ProductDTO;
import com.ecom.product.entity.Product;
import com.ecom.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductDTO> getProducts(Integer page, Integer size, String sortBy, String sort) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();
    }

public ProductDTO deleteProduct(Long productId) {
    Optional<Product> productToDelete = productRepository.findById(productId);
    if (productToDelete.isEmpty()) {
        return null;
    }

    Product product = productToDelete.get();
    productRepository.delete(product);
    return modelMapper.map(product, ProductDTO.class);
}

    }
}
