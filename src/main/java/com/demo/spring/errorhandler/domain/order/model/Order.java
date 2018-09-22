package com.demo.spring.errorhandler.domain.order.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@Value
public class Order {
    @NotNull
    private Integer id;

    private Integer customerId;
}
