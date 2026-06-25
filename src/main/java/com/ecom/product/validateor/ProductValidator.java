package com.ecom.product.validateor;

import com.ecom.product.entity.Product;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {
  private final Validator validator;

  public ProductValidator(Validator validator) {
    this.validator = validator;
  }

  public void validate(Product product) {
    Set<ConstraintViolation<Product>> violations = validator.validate(product);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
