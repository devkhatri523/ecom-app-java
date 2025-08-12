package com.product.product.controller;

import com.product.product.dto.request.ProductPurchaseRequest;
import com.product.product.dto.request.ProductRequest;
import com.product.product.dto.response.ProductPuchaseResponse;
import com.product.product.dto.response.ProductReponse;
import com.product.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid  ProductRequest productRequest){
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @PostMapping("/purchaseproducts")
    public ResponseEntity<List<ProductPuchaseResponse>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequest){
        return  ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequest));

    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductReponse> getProduct(@PathVariable("productId") Integer productId){
        return ResponseEntity.ok(productService.findProductById(productId));

    }

    @GetMapping
    public ResponseEntity<List<ProductReponse>> getProducts(){
        return ResponseEntity.ok(productService.findAllProduct());

    }
}
