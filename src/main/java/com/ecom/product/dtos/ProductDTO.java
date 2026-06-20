package com.ecom.product.dtos;

import com.ecom.product.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private String slug;
    private String description;
    private Integer quantity;
    private Double price;
    private Double discount;
    private String company;
    private Category category;
    private Long seller;
}
