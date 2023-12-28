package com.platform.order.service.config;

import com.platform.order.service.core.repository.OrderItemRepository;
import com.platform.order.service.core.repository.OrderRepository;
import com.platform.order.service.core.service.KafkaService;
import com.platform.order.service.core.service.OrderService;
import com.platform.order.service.core.service.impl.KafkaServiceImpl;
import com.platform.order.service.core.service.impl.OrderServiceImpl;
import com.platform.order.service.core.usecase.OrderUsecase;
import com.platform.order.service.core.usecase.OrderUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public OrderService orderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        return new OrderServiceImpl(orderRepository, orderItemRepository);
    }

    @Bean
    public KafkaService kafkaService() {
        return new KafkaServiceImpl();
    }

    @Bean
    public OrderUsecase orderUsecase(OrderService orderService, KafkaService kafkaService) {
        return new OrderUsecaseImpl(orderService, kafkaService);
    }

}
