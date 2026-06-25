package com.ecom.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  @NotBlank(message = "Category name cannot be null")
  @Size(min = 1, message = "Category name must be at least 1 character long")
  private String name;
}
