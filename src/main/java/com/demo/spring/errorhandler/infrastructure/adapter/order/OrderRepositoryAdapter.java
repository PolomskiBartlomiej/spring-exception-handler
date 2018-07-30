package com.demo.spring.errorhandler.infrastructure.adapter.order;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.port.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class OrderRepositoryAdapter implements OrderRepository {

    private final OrderDatabasesRepository repository;

    @Override
    public Optional<Order> findById(Long orderId) {
        return repository.findById(orderId);
    }
}
