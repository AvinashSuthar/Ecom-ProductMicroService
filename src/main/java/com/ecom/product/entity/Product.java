package com.ecom.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String slug;
    private String description;
    private Integer quantity;
    private Double price;
    private Double discount;
    private String company;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    //TODO: update this with user
    private Long seller;

}
