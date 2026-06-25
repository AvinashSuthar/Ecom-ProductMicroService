package com.ecom.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "Product slug cannot be blank")
    @Column(nullable = false, unique = true)
    private String slug;
    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 10, message = "Product description must be at least 10 characters")
    @Column(nullable = false)
    private String description;
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    @Column(nullable = false)
    private Double price;
    @DecimalMin(value = "0.0", message = "Discount cannot be negative")
    private Double discount;
    @NotBlank(message = "Company name cannot be blank")
    @Column(nullable = false)
    private String company;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    //TODO: update this with user
    @NotNull(message = "Seller ID is required")
    @Column(nullable = false)
    private Long seller;

}
