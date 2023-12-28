package com.platform.order.service.core.service.impl;

import com.platform.order.service.core.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    @Override
    public void publishOrdersMessage(String topic, Object data) {

    }
}
