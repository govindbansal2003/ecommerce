package com.ecommerce.controller;

import java.util.List;

import com.ecommerce.Entity.Product;
import com.ecommerce.exception.ProductException;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req) throws ProductException {
        try {
            Product createdProduct = productService.createProduct(req);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (ProductException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {
        try {
            String msg = productService.deleteProduct(productId);
            ApiResponse res = new ApiResponse(msg, true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ProductException e) {
            ApiResponse res = new ApiResponse("Product not found", false);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Product>> recentlyAddedProduct() {
        List<Product> products = productService.recentlyAddedProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProductHandler(@RequestBody Product req, @PathVariable Long productId) throws ProductException {
        try {
            Product updatedProduct = productService.updateProduct(productId, req);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws ProductException {
        try {
            for (CreateProductRequest product : reqs) {
                productService.createProduct(product);
            }
            ApiResponse res = new ApiResponse("Products created successfully", true);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (ProductException e) {
            ApiResponse res = new ApiResponse("Failed to create products", false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
