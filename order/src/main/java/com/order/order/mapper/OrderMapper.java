package com.order.order.mapper;

import com.order.order.dto.OrderReponse;
import com.order.order.dto.OrderRequest;
import com.order.order.dto.ProductPurchaseRequest;
import com.order.order.dto.PurchaseRequest;
import com.order.order.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public ProductPurchaseRequest toProductPurchaseRequest(PurchaseRequest purchaseRequest) {
        ProductPurchaseRequest productPurchaseRequest = new ProductPurchaseRequest();
        productPurchaseRequest.setProductId(purchaseRequest.getProductId());
        productPurchaseRequest.setQuantity(purchaseRequest.getQuantity());
        return productPurchaseRequest;

    }
    public Order toOrder(OrderRequest orderRequest) {
       return Order.builder().customerId(orderRequest.getCustomerId()).reference(orderRequest.getReference()).
        paymentMethod(orderRequest.getPaymentMethod()).totalAmount(orderRequest.getAmount()).build();
    }

    public OrderReponse toOrderResponse(Order order){
        return OrderReponse.builder().reference(order.getReference()).customerId(order.getCustomerId()).amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod()).build();
    }
}
