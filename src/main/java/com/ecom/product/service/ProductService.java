package com.ecom.product.service;

import com.ecom.product.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts(Integer page, Integer size, String sortBy, String orderBy);

    ProductDTO deleteProduct(Long productId);
}
