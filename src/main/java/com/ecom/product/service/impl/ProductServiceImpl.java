package com.ecom.product.service.impl;

import com.ecom.product.dtos.ProductDTO;
import com.ecom.product.dtos.request.CreateProductRequest;
import com.ecom.product.entity.Product;
import com.ecom.product.exception.NoResourceFoundException;
import com.ecom.product.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper , ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.productMapper = productMapper;

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

    @Override
    public ProductDTO createProduct(CreateProductRequest createProductRequest) {
        Product product = productMapper.mapToProduct(createProductRequest);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProductsByKeyword(String keyword) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);
        if (products.isEmpty()) {
            throw new NoResourceFoundException("No products found with keyword: " + keyword);
        }
        return products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();
    } 
    @Override
    public ProductDTO updateProduct(CreateProductRequest createProductRequest, Long productId) {
        Product product = productMapper.mapToProduct(createProductRequest);
        Optional<Product> savedProduct = productRepository.findById(productId);
        if(savedProduct.isEmpty()) {
            throw new NoResourceFoundException("Product with ID " + productId + " not found");
        }
        product.setProductId(productId);
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }
}
