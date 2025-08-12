package com.ecom.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerRequest {
    @NotBlank(message = "Customer firstname is required")
    private String firstName;
    @NotBlank(message = "Customer lastname is required")
    private String lastName;
    @Email(message = "Customer email is not valid email")
    private String email;
    private AddressDto address;
}
