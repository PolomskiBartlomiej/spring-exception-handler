package com.demo.spring.errorhandler.domain.order.service;

import com.demo.spring.errorhandler.domain.order.exception.NoResultException;
import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.port.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Order findById(@NonNull Integer orderId) {
        return repository
                .findById(orderId)
                .orElseThrow(() -> new NoResultException("No result for order id = " + orderId));
    }
}
