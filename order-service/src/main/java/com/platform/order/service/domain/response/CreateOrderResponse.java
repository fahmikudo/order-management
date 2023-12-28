package com.platform.order.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private Integer orderId;
    private Integer userId;
    private String orderDatetime;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalDiscount;
    private BigDecimal totalSaving;
    private String orderStatus; // CREATED, PROCESSED, COMPLETED, CANCELED
    private String status; // ACTIVE, INACTIVE
}
