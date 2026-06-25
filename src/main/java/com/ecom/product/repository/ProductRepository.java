package com.ecom.product.repository;

import com.ecom.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByNameContainingIgnoreCase(String keyword);
}
