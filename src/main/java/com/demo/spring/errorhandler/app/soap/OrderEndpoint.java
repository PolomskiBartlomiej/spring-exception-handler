package com.demo.spring.errorhandler.app.soap;

import com.demo.spring.errorhandler.domain.order.service.OrderService;
import com.demo.spring.errorhandler.infrastructure.soap.NameSpace;
import com.demo.spring.errorhandler.infrastructure.soap.assembler.OrderWsAssembler;
import com.demo.spring.errorhandler.infrastructure.soap.message.order.GetOrderRequest;
import com.demo.spring.errorhandler.infrastructure.soap.message.order.GetOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
class OrderEndpoint {

    private final OrderService orderService;

    @PayloadRoot(namespace = NameSpace.ORDER, localPart = "getOrderRequest")
    @ResponsePayload GetOrderResponse
    getOrder(@RequestPayload GetOrderRequest request) {
       return OrderWsAssembler.toDto(orderService.findById(request.getId()));
    }
}
