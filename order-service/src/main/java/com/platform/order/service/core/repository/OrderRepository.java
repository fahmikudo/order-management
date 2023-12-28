package com.platform.order.service.core.repository;

import com.platform.order.service.core.persistence.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
