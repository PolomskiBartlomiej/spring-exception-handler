package com.demo.spring.errorhandler.domain.order.port;

import com.demo.spring.errorhandler.domain.order.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long orderId);
}
