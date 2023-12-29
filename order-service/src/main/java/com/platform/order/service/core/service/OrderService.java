package com.platform.order.service.core.service;

import com.platform.order.service.domain.Order;
import com.platform.order.service.domain.request.CreateOrderRequest;
import com.platform.order.service.domain.request.UpdateOrderRequest;

public interface OrderService {
    Order createOrderService(CreateOrderRequest createOrderRequest);

    Order updateOrderStatus(UpdateOrderRequest updateOrderRequest);

}
