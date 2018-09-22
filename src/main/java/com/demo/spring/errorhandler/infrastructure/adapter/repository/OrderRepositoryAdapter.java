package com.demo.spring.errorhandler.infrastructure.adapter.repository;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.port.OrderRepository;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
class OrderRepositoryAdapter implements OrderRepository {

    @Override
    public Optional<Order> findById(@NotNull Integer orderId) {
        switch (orderId) {
            case 0 : throw new IllegalArgumentException("orderId cannot be 0");
            case 1 : return Optional.empty();
            case 2 : return buildOrder(orderId);
            default: throw new IllegalStateException("Illegal state");
        }
    }

    private Optional<Order> buildOrder(Integer orderId) {
        return Optional.of(Order.builder()
                                .id(orderId)
                                .customerId(1)
                                .build());
    }
}
