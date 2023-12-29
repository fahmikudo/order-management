package com.platform.order.service.core.usecase;

import com.platform.order.service.domain.request.CreateOrderRequest;
import com.platform.order.service.domain.request.UpdateOrderRequest;
import com.platform.order.service.domain.response.CreateOrderResponse;

public interface OrderUsecase {
    CreateOrderResponse createOrderUsecase(CreateOrderRequest createOrderRequest);

    CreateOrderResponse updateOrderUseCase(UpdateOrderRequest updateOrderRequest);

}
