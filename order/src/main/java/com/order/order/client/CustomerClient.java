package com.order.order.client;

import com.order.order.dto.CustomerResponse;
import com.order.order.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class CustomerClient {
    @Value("${customer.service.url}")
    private String customerServiceUrl;
    private final RestTemplate restTemplate;





    public CustomerResponse getCustomerInfo(String customerId){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<CustomerResponse> responseType = new ParameterizedTypeReference<CustomerResponse>(){

        };
        ResponseEntity<CustomerResponse> responEntity = restTemplate.exchange(customerServiceUrl+"/"+customerId, HttpMethod.GET,entity,responseType);

        if(responEntity.getStatusCode().isError()){
            throw  new BusinessException("An error occured while processing the get customer request");
        }
        return  responEntity.getBody();

    }


}
