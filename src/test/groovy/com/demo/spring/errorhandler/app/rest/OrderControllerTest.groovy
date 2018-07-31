package com.demo.spring.errorhandler.app.rest

import com.demo.spring.errorhandler.domain.order.service.OrderService
import spock.lang.Specification

class OrderControllerTest extends Specification {
    def "getOrders() only use domain service and not change request"() {
        given:
        def service = Mock(OrderService)
        and:
        def request = 1L
        and:
        def controller = new OrderController(service)

        when:
        controller.getOrders(request)

        then:
        1 * service.findById(request)
        0 * _
    }
}
