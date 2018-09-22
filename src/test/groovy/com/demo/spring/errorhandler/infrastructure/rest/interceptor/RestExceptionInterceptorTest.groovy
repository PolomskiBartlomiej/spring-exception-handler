package com.demo.spring.errorhandler.infrastructure.rest.interceptor

import com.demo.spring.errorhandler.domain.order.exception.NoResultException
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

class RestExceptionInterceptorTest extends Specification {

    def "handleException() : response is depend on Exception"() {
        given:
        def interceptor = new RestExceptionInterceptor()

        and:
        def exception = Mock(_exception) {
            getLocalizedMessage() >> _message
        }

        and:
        def request = Mock(HttpServletRequest) {
            getRequestURI() >> _url
        }

        when:
        def response = interceptor.handleException(request, exception)

        then:
        with(response) {
            url == _url
            message == _message
        }

        where:
        _exception               | _message               | _url
        IllegalArgumentException | "message"              | "url"
        NoResultException        | "message"              | "url"
        Exception                | "Something go wrong !" | "url"
    }

}
