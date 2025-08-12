package com.order.order.dto;

import com.order.order.entities.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderReponse {
    Integer orderId;
    String reference;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    String customerId;

}
