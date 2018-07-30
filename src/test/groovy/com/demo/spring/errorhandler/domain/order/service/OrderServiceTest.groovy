package com.demo.spring.errorhandler.domain.order.service

import com.demo.spring.errorhandler.domain.order.model.Order
import com.demo.spring.errorhandler.domain.order.port.OrderRepository
import com.demo.spring.errorhandler.domain.order.exception.NoResourcesException
import spock.lang.Specification

class OrderServiceTest extends Specification {

    def "findById() thrown IllegalArgumentException given null input "() {
        given:
        def repository = Mock(OrderRepository)

        and:
        def service = new OrderService(repository)

        when:
        service.findById(null)

        then:
        thrown(IllegalArgumentException)
    }

    def "findById() thrown NoResourcesException if no resources in repo"() {
        given:
        def repository = Mock(OrderRepository) {
            findById(1L) >> Optional.empty()
        }

        def service = new OrderService(repository)

        when:
        service.findById(1L)

        then:
        thrown(NoResourcesException)
    }

    def "findById() only use OrderRepository::findById for search for resources"() {
        def repository = Mock(OrderRepository) {
            findById(1L) >> Optional.of(new Order(1L))
        }

        def service = new OrderService(repository)

        when:
        service.findById(1L)

        then:
         1 * repository.findById(1L)
         0 * _
    }
}
