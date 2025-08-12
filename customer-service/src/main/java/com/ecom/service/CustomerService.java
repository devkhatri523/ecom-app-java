package com.ecom.service;

import com.ecom.dto.CustomerRequest;
import com.ecom.dto.CustomerResponse;
import com.ecom.dto.CustomerUpdateRequest;
import com.ecom.entity.Address;
import com.ecom.entity.Customer;
import com.ecom.exception.CustomerNotFoundException;
import com.ecom.mapper.CustomerMapper;
import com.ecom.repository.CustomerRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
    }

    public void updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
        var customer = customerRepository.findById(customerUpdateRequest.getId()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id %s not found", customerUpdateRequest.getId())
        ));
        mergeCustomer(customer, customerUpdateRequest);

    }

    private void mergeCustomer(Customer customer, CustomerUpdateRequest request) {
        if (StringUtils.isNotBlank(request.getFirstName())) {
            customer.setFirstName(request.getFirstName());

        }
        if (StringUtils.isNotBlank(request.getLastName())) {
            customer.setFirstName(request.getLastName());

        }
        if (StringUtils.isNotBlank(request.getEmail())) {
            customer.setFirstName(request.getEmail());
        }
        if (request.getAddress() != null) {
            Address address = Address.builder().houseNumber(request.getAddress().getHouseNumber()).zipCode(request.getAddress().getZipCode()).street(request.getAddress().getStreet()).build();
            customer.setAddress(address);
        }
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();

    }

    public CustomerResponse fidById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id %s not found", customerId)
        ));
    }

    public void deleteById(String customerId) {
        var customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id %s not found", customerId)
        ));
        customerRepository.deleteById(customerId);
    }


}
