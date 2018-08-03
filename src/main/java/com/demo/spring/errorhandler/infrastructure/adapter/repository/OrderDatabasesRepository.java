package com.demo.spring.errorhandler.infrastructure.adapter.repository;

import com.demo.spring.errorhandler.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderDatabasesRepository extends JpaRepository<Order,Long> {
}
