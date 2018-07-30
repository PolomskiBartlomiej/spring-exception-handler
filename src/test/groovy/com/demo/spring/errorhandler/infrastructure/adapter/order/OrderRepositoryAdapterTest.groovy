package com.demo.spring.errorhandler.infrastructure.adapter.order

import spock.lang.Specification

class OrderRepositoryAdapterTest extends Specification {
    def "findById should only invoke order repository findById method"() {
        given:
        def id = 1L

        def repo = Mock(OrderDatabasesRepository) {
            findById(id) >> Optional.empty()
        }

        and:
        def adapter = new OrderRepositoryAdapter(repo)

        when:
        adapter.findById(id)

        then:
        1* repo.findById(id)
        0 * _

    }
}
