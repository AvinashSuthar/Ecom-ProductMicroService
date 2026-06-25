package com.ecom.product.mapper;

import com.ecom.product.dtos.request.CreateProductRequest;
import com.ecom.product.entity.Category;
import com.ecom.product.entity.Product;
import com.ecom.product.repository.CategoryRepository;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  private final CategoryRepository categoryRepository;

  public ProductMapper(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Product mapToProduct(CreateProductRequest createProductRequest) {
    Product product = new Product();
    Category category = categoryRepository.findByName(createProductRequest.categoryName());
    if (category == null) {
      Category newCategory = new Category();
      newCategory.setName(createProductRequest.categoryName());
      category = categoryRepository.save(newCategory);
    }
    product.setName(createProductRequest.name());
    product.setSlug(generateSlug(createProductRequest.name()));
    product.setDescription(createProductRequest.description());
    product.setQuantity(createProductRequest.quantity());
    product.setPrice(createProductRequest.price());
    product.setDiscount(createProductRequest.discount());
    product.setCompany(createProductRequest.company());
    product.setCategory(category);
    return product;
  }

  private String generateSlug(String productName) {
    return productName
        .trim()
        .replaceAll("[^a-z0-9\\s]", "") // Keep spaces
        .replaceAll("\\s+", "-")
        .replaceAll("-+", "-")
        .toLowerCase()
        .concat("-")
        .concat(UUID.randomUUID().toString().substring(0, 8));
  }
}
