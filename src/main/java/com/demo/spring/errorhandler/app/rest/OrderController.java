package com.demo.spring.errorhandler.app.rest;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    Order getOrders(@PathVariable("id") Integer id) {
        return orderService.findById(id);
    }

}
