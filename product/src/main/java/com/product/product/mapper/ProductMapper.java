package com.product.product.mapper;

import com.product.product.dto.request.ProductRequest;
import com.product.product.dto.response.ProductPuchaseResponse;
import com.product.product.dto.response.ProductReponse;
import com.product.product.entities.Category;
import com.product.product.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest request){
        Product product = new Product();
        Category category = new Category();
        product.setName(request.getProductName());
        product.setDescription(request.getProductDescription());
        product.setAvailableQuantity(request.getAvailableQuantity());
        product.setPrice(request.getPrice());
        category.setCategoryId(request.getCategoryId());
        product.setCategory(category);
        return product;
    }

    public ProductReponse toProductResponse(Product product){
        ProductReponse productResponse = new ProductReponse();
        productResponse.setId(product.getId());
        productResponse.setCategoryId(product.getCategory().getCategoryId());
        productResponse.setCategoryName(product.getCategory().getCategoryName());
        productResponse.setCategoryDescription(product.getCategory().getDescription());
        productResponse.setAvailableQuantity(product.getAvailableQuantity());
        productResponse.setPrice(product.getPrice());
        productResponse.setProductName(product.getName());
        productResponse.setProductDescription(product.getDescription());
        return productResponse;
    }

    public ProductPuchaseResponse toProductPuchaseResponse(Product product,double quantity){
        ProductPuchaseResponse productPuchaseResponse = new ProductPuchaseResponse();
        productPuchaseResponse.setProductId(product.getId());
        productPuchaseResponse.setProductName(product.getName());
        productPuchaseResponse.setProductDescription(product.getDescription());
        productPuchaseResponse.setProductQuantity(quantity);
        productPuchaseResponse.setProductPrice(product.getPrice());
        return  productPuchaseResponse;



    }
}
