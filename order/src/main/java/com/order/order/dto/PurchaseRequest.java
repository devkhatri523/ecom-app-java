package com.order.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Validated
public class PurchaseRequest {
    @Positive(message = "Product is mandatory")
    private Integer productId;
    @Positive(message = "Quantity is mandatory")
    private double quantity;
}
