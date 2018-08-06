package com.demo.spring.errorhandler.infrastructure.soap.assembler;

import com.demo.spring.errorhandler.domain.order.model.Order;
import com.demo.spring.errorhandler.infrastructure.soap.message.order.GetOrderResponse;
import com.demo.spring.errorhandler.infrastructure.soap.message.order.OrderWs;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class OrderWsAssembler {

    private static final ModelMapper MAPPER = new ModelMapper();

    static {
        MAPPER.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static GetOrderResponse toDto(Order entity) {
        OrderWs orderWs = MAPPER.map(entity, OrderWs.class);
        return new GetOrderResponse(orderWs);
    }

}
