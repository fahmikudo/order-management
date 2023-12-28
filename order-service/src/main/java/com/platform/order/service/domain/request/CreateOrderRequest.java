package com.platform.order.service.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private Integer userId;
    private List<OrderItems> orderItems;
    private BigDecimal totalPrice;
    private BigDecimal totalTax;
    private BigDecimal totalDiscount;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItems {
        private String productName;
        private String productCategory;
        private Integer productAmount;
        private BigDecimal productPrice;
        private Integer productId;
        private String productDiscountCode;
        private BigDecimal productTax;
        private BigDecimal productDiscount;
    }
}
