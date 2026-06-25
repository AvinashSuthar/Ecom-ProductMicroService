package com.ecom.product.validateor;

import com.ecom.product.entity.Product;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductValidator {
    private final Validator  validator;
    public ProductValidator(Validator validator) {
        this.validator = validator;
    }
    public void validate(Product product){
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
