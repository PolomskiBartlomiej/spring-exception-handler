package com.demo.spring.errorhandler.app.rest;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
class OrderController {
    private OrderService orderService;

    @GetMapping("/{id}")
    Order getOrders(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

}
