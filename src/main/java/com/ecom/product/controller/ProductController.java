package com.ecom.product.controller;

import com.ecom.product.constants.Constant;
import com.ecom.product.dtos.ProductDTO;
import com.ecom.product.dtos.request.CreateProductRequest;
import com.ecom.product.response.APIResponse;
import com.ecom.product.service.ProductService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping("/{productId}")
  public ResponseEntity<APIResponse<ProductDTO>> getProduct(@PathVariable Long productId) {
    ProductDTO productDTO = productService.getProductById(productId);
    return new ResponseEntity<>(
        new APIResponse<>(true, "Product fetched successfully", productDTO), HttpStatus.OK);

  }

  @GetMapping
  public ResponseEntity<APIResponse<List<ProductDTO>>> getProducts(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "10") Integer size,
      @RequestParam(value = "sortBy", defaultValue = Constant.SORT_BY) String sortBy,
      @RequestParam(value = "orderBy", defaultValue = Constant.ORDER_BY) String orderBy) {
    List<ProductDTO> productDTO = productService.getProducts(page, size, sortBy, orderBy);
    return new ResponseEntity<>(
        new APIResponse<>(true, "Products fetched successfully", productDTO), HttpStatus.OK);
  }

  @GetMapping("/keyword/{keyword}")
  public ResponseEntity<APIResponse<List<ProductDTO>>> getProductsByKeyword(
      @PathVariable("keyword") String keyword) {
    List<ProductDTO> productDTO = productService.getProductsByKeyword(keyword);
    return new ResponseEntity<>(
        new APIResponse<>(true, "Products fetched successfully", productDTO), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<APIResponse<ProductDTO>> createProduct(
      @RequestBody CreateProductRequest createProductRequest) {
    ProductDTO createdProduct = productService.createProduct(createProductRequest);
    return new ResponseEntity<>(
        new APIResponse<>(true, "Product created successfully", createdProduct),
        HttpStatus.CREATED);
  }

  @PutMapping("/{productId}")
  public ResponseEntity<APIResponse<ProductDTO>> updateProduct(
      @PathVariable("productId") Long productId,
      @RequestBody CreateProductRequest createProductRequest) {
    ProductDTO updatedProduct = productService.updateProduct(createProductRequest, productId);
    return new ResponseEntity<>(
        new APIResponse<>(true, "Product updated successfully", updatedProduct), HttpStatus.OK);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<APIResponse<ProductDTO>> deleteProduct(
      @PathVariable("productId") Long productId) {
    ProductDTO deletedProduct = productService.deleteProduct(productId);
    return new ResponseEntity<>(
        new APIResponse<>(true, "Product deleted successfully", deletedProduct), HttpStatus.OK);
  }

}
