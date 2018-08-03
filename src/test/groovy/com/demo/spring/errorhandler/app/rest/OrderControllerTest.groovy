package com.demo.spring.errorhandler.app.rest

import com.demo.spring.errorhandler.domain.order.model.Order
import com.demo.spring.errorhandler.domain.order.service.OrderService
import spock.lang.Specification

class OrderControllerTest extends Specification {
    def "getOrders() only use domain service and not change request"() {
        given:
        def service = Mock(OrderService)
        and:
        def controller = new OrderController(service)
        when:
        def response = controller.getOrders(_request)

        then:
        1 * service.findById(_request) >> _order
        0 * _
        response == _order

        where:
        _request | _order
        1        | new Order(1, 2)
    }
}
