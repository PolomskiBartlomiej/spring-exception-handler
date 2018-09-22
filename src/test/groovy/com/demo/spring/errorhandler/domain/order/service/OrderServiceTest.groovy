package com.demo.spring.errorhandler.domain.order.service

import com.demo.spring.errorhandler.domain.order.exception.NoResultException
import com.demo.spring.errorhandler.domain.order.model.Order
import com.demo.spring.errorhandler.domain.order.port.OrderRepository
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

    def "findById() thrown NoResultException if no resources in repo"() {
        given:
        def repository = Mock(OrderRepository) {
            findById(_id) >> Optional.empty()
        }

        def service = new OrderService(repository)

        when:
        service.findById(_id)

        then:
        thrown(NoResultException)

        where:
        _id = 1
    }

    def "findById() only use OrderRepository::findById for search for resources"() {
        def repository = Mock(OrderRepository)

        def service = new OrderService(repository)

        when:
        service.findById(_id)

        then:
         1 * repository.findById(_id) >> Optional.of(new Order(1,1))
         0 * _

        where:
        _id = 1
    }
}
