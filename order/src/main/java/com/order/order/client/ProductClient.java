package com.order.order.client;

import com.order.order.dto.ProductPuchaseResponse;
import com.order.order.dto.ProductPurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${purchase.product.url}")
    private String productServiceUrl;
    private final WebClient webClient;

    public List<ProductPuchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList){
        return webClient.post().uri(productServiceUrl+"/purchaseproducts")
                .bodyValue(requestList).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductPuchaseResponse>>() {}).block();

    }





}
