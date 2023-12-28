package com.platform.order.service.core.repository;

import com.platform.order.service.core.persistence.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemsEntity, Integer> {
}
