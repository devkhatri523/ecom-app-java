package com.order.order.testdata;

import com.order.order.dto.*;
import com.order.order.entities.Order;
import com.order.order.entities.OrderLine;
import com.order.order.entities.PaymentMethod;
import org.assertj.core.util.Arrays;

import java.math.BigDecimal;
import java.util.List;

public class TestUtil {

    public static OrderRequest createOrderRequest(){
        return OrderRequest.builder().reference("REF-1").amount(BigDecimal.valueOf(200))
                .customerId("CUST-1").paymentMethod(PaymentMethod.PAYPAL).purchaseRequests(
                        List.of(createPurchaseRequest())).build();

    }
    public static PurchaseRequest createPurchaseRequest(){
        return PurchaseRequest.builder().productId(1).quantity(20.0).build();
    }

    public static OrderReponse createOrderResponse(){
        return OrderReponse.builder().orderId(1).customerId("CUST-1").reference("REF-1").amount(BigDecimal.valueOf(200))
                .customerId("CUST-1").build();
    }

    public static CustomerResponse createCustomerResponse(){
        return CustomerResponse.builder().firstName("CUST-FIRTNAME").lastName("CUST-LASTNAME")
                .email("CUST-EMAIL").id("CUST-1").build();
    }
    public static ProductPuchaseResponse createProductPuchaseResponse(){
        ProductPuchaseResponse productPuchaseResponse = new ProductPuchaseResponse();
        productPuchaseResponse.setProductId(1);
        productPuchaseResponse.setProductName("PRODUCT NAME");
        productPuchaseResponse.setProductPrice(BigDecimal.valueOf(200));
        return productPuchaseResponse;
    }

    public static Order createOrder(){
        return Order.builder().id(10).build();
    }

    public static ProductPurchaseRequest  productPurchaseRequest(){
       ProductPurchaseRequest productPurchaseRequest = new ProductPurchaseRequest();
       productPurchaseRequest.setProductId(1);
       productPurchaseRequest.setQuantity(12);
       return productPurchaseRequest;
    }
}
