package com.ecom.product.service.impl;

import com.ecom.product.dtos.ProductDTO;
import com.ecom.product.entity.Product;
import com.ecom.product.exception.NoResourceFoundException;
import com.ecom.product.repository.ProductRepository;
import com.ecom.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> getProducts(Integer page, Integer size, String sortBy, String sort) {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NoResourceFoundException("No products found");
        }
        return products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Optional<Product> productToDelete = productRepository.findById(productId);
        if (productToDelete.isEmpty()) {
            throw new NoResourceFoundException("Product with ID " + productId + " not found");
        }
        Product product = productToDelete.get();
        productRepository.delete(product);
        return modelMapper.map(product, ProductDTO.class);
    }


}
