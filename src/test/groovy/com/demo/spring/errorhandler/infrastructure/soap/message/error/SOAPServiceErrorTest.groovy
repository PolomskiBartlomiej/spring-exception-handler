package com.demo.spring.errorhandler.infrastructure.soap.message.error

import spock.lang.Specification

import javax.persistence.NoResultException

class SOAPServiceErrorTest extends Specification {

    def "SOAPServiceError() create correct object "() {
        when:
        def serviceError = new SOAPServiceError(_exception)

        then:
        serviceError.getCode() == _code
        serviceError.getMessage() == _message

        where:

        _exception                              | _code         | _message
        new IllegalArgumentException("message") | "BAD_REQUEST" | "message"
        new NoResultException("message")        | "NO_RESULT"   | "message"
        new IllegalStateException("message")    | "ERROR"       | "Something go wrong"

    }
}
