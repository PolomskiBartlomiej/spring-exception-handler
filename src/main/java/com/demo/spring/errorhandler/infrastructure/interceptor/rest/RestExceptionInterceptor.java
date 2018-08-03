package com.demo.spring.errorhandler.infrastructure.interceptor.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = RestController.class)
class RestExceptionInterceptor {

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    RestIllegalArgumentJson handleException(HttpServletRequest request,
                                            IllegalArgumentException exception) {
        return RestIllegalArgumentJson.builder()
                .url(request.getRequestURI())
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    @ResponseBody
    RestIllegalArgumentJson handleException(HttpServletRequest request,
                                            NoResultException exception) {
        return RestIllegalArgumentJson.builder()
                .url(request.getRequestURI())
                .message(exception.getMessage())
                .build();
    }

}
