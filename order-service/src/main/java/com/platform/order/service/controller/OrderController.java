package com.platform.order.service.controller;

import com.platform.order.service.core.usecase.OrderUsecase;
import com.platform.order.service.domain.request.CreateOrderRequest;
import com.platform.order.service.domain.response.CreateOrderResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderUsecase orderUsecase;

    @PostMapping(value = "platform/create-order")
    public ResponseEntity<CreateOrderResponse> createOrdersRest(
        @RequestBody CreateOrderRequest createOrderRequest)
    {
        CreateOrderResponse response = orderUsecase
            .createOrderUsecase(createOrderRequest);

        return ResponseEntity.ok(response);
    }
}
