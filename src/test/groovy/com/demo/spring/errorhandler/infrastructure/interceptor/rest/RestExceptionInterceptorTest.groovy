package com.demo.spring.errorhandler.infrastructure.interceptor.rest

import spock.lang.Shared
import spock.lang.Specification

import javax.persistence.NoResultException
import javax.servlet.http.HttpServletRequest

class RestExceptionInterceptorTest extends Specification {

    @Shared
    def  _url = "url"
    @Shared
    def _message = "message"
    @Shared
    def request = Mock(HttpServletRequest) {
        getRequestURI() >> _url
    }

    def "handleException() catch IllegalArgumentException with request url and exception message"() {
        given:
        def exception = Mock(IllegalArgumentException) {
            getMessage() >> _message
        }

        def interceptor = new RestExceptionInterceptor()

        when:
        def response = interceptor.handleException(request,exception)

        then:
        with(response) {
            url == _url
            message == _message
        }
    }

    def "handleException() catch NoResultException with request url and exception message"() {
        given:
        def exception = Mock(NoResultException) {
            getMessage() >> _message
        }

        def interceptor = new RestExceptionInterceptor()

        when:
        def response = interceptor.handleException(request,exception)

        then:
        with(response) {
            url == _url
            message == _message
        }
    }

}
