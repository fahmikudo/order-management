package com.platform.order.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private Integer userId;
    private Timestamp orderDatetime;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalDiscount;
    private BigDecimal totalSaving;
    private String orderStatus; // CREATED, PROCESSED, COMPLETED, CANCELED
    private String status; // ACTIVE, INACTIVE
}
