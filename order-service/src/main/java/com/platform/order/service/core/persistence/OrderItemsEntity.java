package com.platform.order.service.core.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_amount")
    private Integer productAmount;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_discount_code")
    private String productDiscountCode;

    @Column(name = "product_tax")
    private BigDecimal productTax;

    @Column(name = "product_discount")
    private BigDecimal productDiscount;
}
