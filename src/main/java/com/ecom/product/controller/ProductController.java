package com.ecom.product.controller;


import com.ecom.product.apiresponse.APIResponse;
import com.ecom.product.constants.Constant;
import com.ecom.product.dtos.ProductDTO;
import com.ecom.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<APIResponse<List<ProductDTO>>> getProducts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = Constant.SORT_BY) String sortBy,
            @RequestParam(value = "orderBy", defaultValue = Constant.ORDER_BY) String orderBy
    ) {
            List<ProductDTO> productDTO =  productService.getProducts(page, size, sortBy , orderBy);
            return new ResponseEntity<>(new APIResponse<>(true, "Products fetched successfully", productDTO), HttpStatus.OK);
    }
@DeleteMapping("/products/{productId}")
    public ResponseEntity<APIResponse<ProductDTO>> deleteProduct(@PathVariable("productId") Long productId) {
        ProductDTO deletedProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(new APIResponse<>(true, "Product deleted successfully", deletedProduct ), HttpStatus.OK);
    }
}
