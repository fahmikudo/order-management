package com.platform.order.service.controller;

import com.platform.order.service.core.usecase.OrderUsecase;
import com.platform.order.service.domain.request.CreateOrderRequest;
import com.platform.order.service.domain.request.UpdateOrderRequest;
import com.platform.order.service.domain.response.CreateOrderResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Orders")
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderUsecase orderUsecase;

    @PostMapping(value = "/platform/create-order")
    public ResponseEntity<CreateOrderResponse> createOrdersRest(
        @RequestBody CreateOrderRequest createOrderRequest)
    {
        CreateOrderResponse response = orderUsecase
            .createOrderUsecase(createOrderRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/platform/{orderId}/update-order-status")
    public ResponseEntity<CreateOrderResponse> updateOrderRest(
            @PathVariable Integer orderId,
            @RequestBody UpdateOrderRequest updateOrderRequest) {

        updateOrderRequest.setOrderId(orderId);

        CreateOrderResponse response = orderUsecase.updateOrderUseCase(updateOrderRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
