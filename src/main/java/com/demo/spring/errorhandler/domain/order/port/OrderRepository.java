package com.demo.spring.errorhandler.domain.order.port;

import com.demo.spring.errorhandler.domain.order.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Integer orderId);
}
