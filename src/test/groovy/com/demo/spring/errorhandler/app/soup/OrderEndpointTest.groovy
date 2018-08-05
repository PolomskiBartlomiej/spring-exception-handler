package com.demo.spring.errorhandler.app.soup

import com.demo.spring.errorhandler.domain.order.model.Order
import com.demo.spring.errorhandler.domain.order.service.OrderService
import com.demo.spring.errorhandler.infrastructure.soap.message.order.GetOrderRequest
import spock.lang.Specification

class OrderEndpointTest extends Specification {
    def "GetOrder using OrderService to receive resources"() {
        given:
        def service = Mock(OrderService)

        def request = Mock(GetOrderRequest)

        and:
        def endpoint = new OrderEndpoint(service)

        when:
        endpoint.getOrder(request)

        then:
        1* service.findById(_) >> new Order()
    }
}
