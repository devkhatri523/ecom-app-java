package com.ecom.mapper;

import com.ecom.dto.AddressDto;
import com.ecom.dto.CustomerRequest;
import com.ecom.dto.CustomerResponse;
import com.ecom.entity.Address;
import com.ecom.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

   public Customer toCustomer(CustomerRequest customerRequest){
       Address address = Address.builder().
               street(customerRequest.getAddress().getStreet()).zipCode(customerRequest.getAddress().
                       getZipCode()).houseNumber(customerRequest.getAddress().getHouseNumber())
               .build();
       if(customerRequest == null){
           return null;
       }
       return Customer.builder().
               firstName(customerRequest.getFirstName()).
               lastName(customerRequest.getLastName()).
               email(customerRequest.getEmail()).address(address).
       build();

    }
   public CustomerResponse fromCustomer(Customer customer){
       AddressDto addressDto = AddressDto.builder().street(customer.getAddress().getStreet()).
               houseNumber(customer.getAddress().getHouseNumber()).zipCode(customer.getAddress().getZipCode()).build();
       return CustomerResponse.builder().id(customer.getId()).firstName(customer.getFirstName()).
               lastName(customer.getLastName()).email(customer.getEmail()).address(addressDto).build();

    }

}
