package com.ecom.product.dtos.request;

public record CreateProductRequest(
        String name,
        String slug,
        String description,
        Integer quantity,
        Double price,
        Double discount,
        String company,
        String categoryName
) {
}
