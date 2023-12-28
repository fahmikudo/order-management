package com.platform.order.service.core.service;

public interface KafkaService {
    void publishOrdersMessage(String topic, Object data);
}
