package com.platform.order.service.core.service.impl;

import com.platform.order.service.core.persistence.OrderEntity;
import com.platform.order.service.core.persistence.OrderItemsEntity;
import com.platform.order.service.core.persistence.OrderStatus;
import com.platform.order.service.core.repository.OrderItemRepository;
import com.platform.order.service.core.repository.OrderRepository;
import com.platform.order.service.core.service.OrderService;
import com.platform.order.service.domain.Order;
import com.platform.order.service.domain.request.CreateOrderRequest;
import com.platform.order.service.domain.request.UpdateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // add transactional process
    @Override
    @Transactional(rollbackFor = {ResponseStatusException.class, Exception.class})
    public Order createOrderService(CreateOrderRequest createOrderRequest) {
        OrderEntity buildOrderEntity = transformOrderEntityRequest(createOrderRequest);
        OrderEntity orderEntity = orderRepository.saveAndFlush(buildOrderEntity);
        createOrderItemEntity(createOrderRequest, orderEntity.getOrderId());
        return transformOrderResult(orderEntity);
    }

    @Override
    @Transactional(rollbackFor = {ResponseStatusException.class, Exception.class})
    public Order updateOrderStatus(UpdateOrderRequest updateOrderRequest) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(updateOrderRequest.getOrderId());
        if (orderEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order with orderId " + updateOrderRequest.getOrderId() + " not found.");

        orderEntity.get().setOrderStatus(updateOrderRequest.getOrderStatus());
        OrderEntity updateOrderEntity = orderRepository.saveAndFlush(orderEntity.get());
        return transformOrderResult(updateOrderEntity);
    }

    private void createOrderItemEntity(CreateOrderRequest createOrderRequest, Integer orderId) {
        for (CreateOrderRequest.OrderItems orderItems: createOrderRequest.getOrderItems()) {
            OrderItemsEntity orderItemsEntity = new OrderItemsEntity();
            orderItemsEntity.setUserId(createOrderRequest.getUserId());
            orderItemsEntity.setOrderId(orderId);
            orderItemsEntity.setProductId(orderItems.getProductId());
            orderItemsEntity.setProductName(orderItems.getProductName());
            orderItemsEntity.setProductCategory(orderItems.getProductCategory());
            orderItemsEntity.setProductAmount(orderItems.getProductAmount());
            orderItemsEntity.setProductPrice(orderItems.getProductPrice());
            orderItemsEntity.setProductDiscountCode(orderItems.getProductDiscountCode());
            orderItemsEntity.setProductTax(orderItems.getProductTax());
            orderItemsEntity.setProductDiscount(orderItems.getProductDiscount());
            orderItemRepository.save(orderItemsEntity);
        }
    }

    private Order transformOrderResult(OrderEntity orderEntity) {
        Order order = new Order();
        order.setOrderId(orderEntity.getOrderId());
        order.setUserId(orderEntity.getUserId());
        order.setTotalPrice(orderEntity.getTotalPrice());
        order.setTotalTax(orderEntity.getTotalTax());
        order.setTotalDiscount(orderEntity.getTotalDiscount());
        order.setTotalSaving(orderEntity.getTotalSaving());
        order.setOrderStatus(orderEntity.getOrderStatus());
        order.setStatus(orderEntity.getStatus());
        order.setOrderDatetime(orderEntity.getOrderDatetime());
        return order;
    }

    private OrderEntity transformOrderEntityRequest(CreateOrderRequest createOrderRequest) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(createOrderRequest.getUserId());
        orderEntity.setOrderDatetime(Timestamp.valueOf(LocalDateTime.now()));
        orderEntity.setTotalPrice(createOrderRequest.getTotalPrice());
        orderEntity.setTotalTax(createOrderRequest.getTotalTax());
        orderEntity.setTotalDiscount(createOrderRequest.getTotalDiscount());
        orderEntity.setTotalSaving(calculateSavingPrice(createOrderRequest.getTotalPrice(), createOrderRequest.getTotalTax(), createOrderRequest.getTotalDiscount()));
        orderEntity.setOrderStatus(String.valueOf(OrderStatus.CREATED));
        orderEntity.setStatus("ACTIVE");
        orderEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        orderEntity.setCreatedBy(String.valueOf(createOrderRequest.getUserId()));
        return orderEntity;
    }

    private BigDecimal calculateSavingPrice(BigDecimal totalPrice, BigDecimal totalTax, BigDecimal totalDiscount) {
        return totalPrice.subtract(totalTax.add(totalDiscount));
    }

}
