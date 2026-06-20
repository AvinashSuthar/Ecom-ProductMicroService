package com.ecom.product.service;

import com.ecom.product.config.ModelMapperConfig;
import com.ecom.product.dtos.ProductDTO;
import com.ecom.product.entity.Product;
import com.ecom.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getProducts(Integer page, Integer size, String sortBy, String sort) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();
    }

    public ProductDTO deleteProduct(Long productId) {
        Optional<Product> productToDelete = productRepository.findById(productId);
        productRepository.deleteById(productId);
        return productToDelete.map(product -> modelMapper.map(product, ProductDTO.class)).orElse(null);

    }
}
