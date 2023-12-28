package com.platform.order.service.core.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_datetime")
    private Timestamp orderDatetime;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "total_tax")
    private BigDecimal totalTax;

    @Column(name = "total_discount")
    private BigDecimal totalDiscount;

    @Column(name = "total_saving")
    private BigDecimal totalSaving;

    @Column(name = "order_status")
    private String orderStatus; // CREATED, PROCESSED, COMPLETED, CANCELED

    @Column(name = "status")
    private String status; // ACTIVE, INACTIVE

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
