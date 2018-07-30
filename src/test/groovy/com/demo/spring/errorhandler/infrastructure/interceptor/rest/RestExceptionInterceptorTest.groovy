package com.demo.spring.errorhandler.infrastructure.interceptor.rest

import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

class RestExceptionInterceptorTest extends Specification {
    def "process() should response with request url and exception message"() {
        given:
        def  _url = "url"
        def _message = "message"

        def request = Mock(HttpServletRequest) {
            getRequestURI() >> _url
        }

        def exception = Mock(IllegalArgumentException) {
            getMessage() >> _message
        }

        def interceptor = new RestExceptionInterceptor()

        when:
        def response = interceptor.process(request,exception)

        then:
        with(response) {
            url == _url
            message == _message
        }
    }

}
