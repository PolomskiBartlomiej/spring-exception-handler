package com.demo.spring.errorhandler.domain.order.service;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.port.OrderRepository;
import com.demo.spring.errorhandler.domain.order.exception.NoResourcesException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repository;

    public Order findById(@NonNull Long orderId) {
        return repository
                .findById(orderId)
                .orElseThrow(() -> new NoResourcesException(orderId));
    }
}
