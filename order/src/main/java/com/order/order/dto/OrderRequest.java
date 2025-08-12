package com.order.order.dto;

import com.order.order.entities.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Validated
public class OrderRequest {
    String reference;
    @Positive(message = "Order amount should be positive")
    BigDecimal amount;
    @NotBlank(message = "Payment method required")
    PaymentMethod paymentMethod;
    @NotBlank(message = "Customer Id required")
    String customerId;
    @NotEmpty(message = "Purchase product should have at least one product")
    List<PurchaseRequest> purchaseRequests;
}
