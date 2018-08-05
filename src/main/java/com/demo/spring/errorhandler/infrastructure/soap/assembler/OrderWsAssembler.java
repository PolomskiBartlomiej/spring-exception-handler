package com.demo.spring.errorhandler.infrastructure.soap.assembler;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.infrastructure.soap.message.order.GetOrderResponse;
import com.demo.spring.errorhandler.infrastructure.soap.message.order.OrderWs;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class OrderWsAssembler {

    private static final ModelMapper MAPPER = new ModelMapper();

    public static GetOrderResponse toDto(Order entity) {
        configurationStrict();
        OrderWs orderWs = MAPPER.map(entity, OrderWs.class);
        return new GetOrderResponse(orderWs);
    }

    private static void configurationStrict() {
        MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
