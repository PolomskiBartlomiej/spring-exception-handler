package com.demo.spring.errorhandler.infrastructure.interceptor.rest

import com.demo.spring.errorhandler.domain.order.exception.NoResourcesException
import spock.lang.Shared
import spock.lang.Specification

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

    def "handleException() catch NoResourcesException with request url and exception message"() {
        given:
        def exception = Mock(NoResourcesException) {
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
