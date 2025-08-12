package com.product.product.service;

import com.product.product.dto.request.ProductPurchaseRequest;
import com.product.product.dto.request.ProductRequest;
import com.product.product.dto.response.ProductPuchaseResponse;
import com.product.product.dto.response.ProductReponse;
import com.product.product.entities.Product;
import com.product.product.exception.PurchaseProductException;
import com.product.product.mapper.ProductMapper;
import com.product.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    public Integer addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductReponse> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).collect(Collectors.toList());

    }

    public ProductReponse findProductById(Integer id) {
        return productRepository.findById(id).map(productMapper::toProductResponse).orElseThrow(() ->
                new EntityNotFoundException("product not found with Id:: " + id));
    }


    public List<ProductPuchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = request.stream().map(ProductPurchaseRequest::getProductId).collect(Collectors.toList());
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        List<ProductPuchaseResponse> response = new ArrayList<>();
        var purchaseProductRequests = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::getProductId)).toList();
        if (productIds.size() != storedProducts.size()) {
            throw new PurchaseProductException("one or more product does not exists  ");
        }
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var indvProductPurchaseRequest = purchaseProductRequests.get(i);
            if (product.getAvailableQuantity() < indvProductPurchaseRequest.getQuantity()) {
                throw new PurchaseProductException("Insufficient stock quantity for product with Id::  "+ product.getId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - indvProductPurchaseRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            response.add(productMapper.toProductPuchaseResponse(product,indvProductPurchaseRequest.getQuantity()));
        }
        return response;
    }


}
