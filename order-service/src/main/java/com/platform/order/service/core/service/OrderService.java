package com.platform.order.service.core.service;

import com.platform.order.service.domain.Order;
import com.platform.order.service.domain.request.CreateOrderRequest;

public interface OrderService {
    Order createOrderService(CreateOrderRequest createOrderRequest);
}
