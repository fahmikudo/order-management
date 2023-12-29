package com.platform.order.service.core.usecase;

import com.platform.order.service.core.service.KafkaService;
import com.platform.order.service.core.service.OrderService;
import com.platform.order.service.domain.Order;
import com.platform.order.service.domain.request.CreateOrderRequest;
import com.platform.order.service.domain.request.UpdateOrderRequest;
import com.platform.order.service.domain.response.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderUsecaseImpl implements OrderUsecase {

    private final OrderService orderService;
    private final KafkaService kafkaService;

    // add transactional process
    // add transaction locking handle race condition
    @Override
    public CreateOrderResponse createOrderUsecase(CreateOrderRequest createOrderRequest) {
        Order order = orderService.createOrderService(createOrderRequest);

        // notify to kafka exchange
        kafkaService.publishOrdersMessage("publish-order-id-0001201010", order);

        // notify to email user for payment
        // emailService.publishEmailCustomer(email, order);

        return transformCreateOrderResponse(order);
    }

    @Override
    public CreateOrderResponse updateOrderUseCase(UpdateOrderRequest updateOrderRequest) {

        Order order = orderService.updateOrderStatus(updateOrderRequest);

        kafkaService.publishOrdersMessage("publish-order-id-0001201010", order);

        return transformCreateOrderResponse(order);
    }

    private CreateOrderResponse transformCreateOrderResponse(Order order) {
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        createOrderResponse.setOrderId(order.getOrderId());
        createOrderResponse.setUserId(order.getUserId());
        createOrderResponse.setStatus(order.getStatus());
        createOrderResponse.setTotalPrice(order.getTotalPrice());
        createOrderResponse.setTotalTax(order.getTotalTax());
        createOrderResponse.setTotalDiscount(order.getTotalDiscount());
        createOrderResponse.setTotalSaving(order.getTotalSaving());
        createOrderResponse.setOrderStatus(order.getOrderStatus());

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        createOrderResponse.setOrderDatetime(dateFormat.format(order.getOrderDatetime()));
        return createOrderResponse;
    }

}
