package com.demo.spring.errorhandler.infrastructure.rest.interceptor;

import com.demo.spring.errorhandler.domain.order.exception.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
class RestExceptionInterceptor {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) MessageExceptionJson
    handleException(HttpServletRequest request, IllegalArgumentException exception) {
        return MessageExceptionJson.builder()
                .url(request.getRequestURI())
                .message(exception.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class) MessageExceptionJson
    handleException(HttpServletRequest request, NoResultException exception) {
        return MessageExceptionJson.builder()
                .url(request.getRequestURI())
                .message(exception.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler MessageExceptionJson
    handleException(HttpServletRequest request, Exception exception) {
        return MessageExceptionJson.builder()
                .url(request.getRequestURI())
                .message("Something go wrong !")
                .build();
    }

}
