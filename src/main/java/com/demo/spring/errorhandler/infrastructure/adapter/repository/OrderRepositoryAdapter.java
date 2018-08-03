package com.demo.spring.errorhandler.infrastructure.adapter.repository;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.port.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class OrderRepositoryAdapter implements OrderRepository {

    private final OrderDatabasesRepository repository;

    @Override
    public Optional<Order> findById(Long orderId) {
        return repository.findById(orderId);
    }
}
