package com.demo.spring.errorhandler.infrastructure.adapter.repository

import spock.lang.Specification

class OrderRepositoryAdapterTest extends Specification {
    def "findById should only invoke order repository findById method"() {
        given:
        def repo = Mock(OrderDatabasesRepository) {
            findById(_id) >> Optional.empty()
        }

        and:
        def adapter = new OrderRepositoryAdapter(repo)

        when:
        adapter.findById(_id)

        then:
        1* repo.findById(_id)
        0 * _

        where:
        _id = 1
    }
}
