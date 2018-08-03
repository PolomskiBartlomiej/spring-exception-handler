package com.demo.spring.errorhandler.app.soup;

import com.demo.spring.errorhandler.domain.order.service.OrderService;
import com.demo.spring.errorhandler.infrastructure.soap.assembler.OrderWsAssembler;
import com.demo.spring.errorhandler.infrastructure.soap.NameSpace;
import com.demo.spring.errorhandler.infrastructure.soap.message.GetOrderRequest;
import com.demo.spring.errorhandler.infrastructure.soap.message.GetOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@AllArgsConstructor
public class OrderEndpoint {

    private final OrderService orderService;


    @PayloadRoot(namespace = NameSpace.ORDER, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
       return OrderWsAssembler.toDto(orderService.findById(request.getId()));
    }
}