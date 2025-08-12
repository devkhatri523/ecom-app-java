package com.order.order.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Validated
@Entity
@Table(name = "order_line")
public class OrderLine {
    @Id
    @SequenceGenerator(
            name = "order_line_seq",
            sequenceName = "order_line_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_seq")
    private Integer id;
    private Integer productId;
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
