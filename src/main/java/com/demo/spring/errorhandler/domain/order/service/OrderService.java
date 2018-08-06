package com.demo.spring.errorhandler.domain.order.service;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.port.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Order findById(@NonNull Long orderId) {
        return repository
                .findById(orderId)
                .orElseThrow(() -> new NoResultException(orderId.toString()));
    }
}
