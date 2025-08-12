package com.order.order.client;

import com.order.order.dto.CustomerResponse;
import com.order.order.dto.PaymentRequest;
import com.order.order.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    @Value("${payment.service.url}")
    private String payemntServiceUrl;
    private final RestTemplate restTemplate;

    public Integer createPayment(PaymentRequest paymentRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest,headers);
        ParameterizedTypeReference<Integer> responseType = new ParameterizedTypeReference<Integer>(){

        };
        ResponseEntity<Integer> responEntity = restTemplate.exchange(payemntServiceUrl, HttpMethod.POST,entity,responseType);

        if(responEntity.getStatusCode().isError()){
            throw  new BusinessException("An error occured while processing the get customer request");
        }
        return  responEntity.getBody();

    }
}
