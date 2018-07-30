package com.demo.spring.errorhandler.infrastructure.interceptor.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = RestController.class)
class RestExceptionInterceptor {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    RestIllegalArgumentJson process(HttpServletRequest request,
                                    IllegalArgumentException exception) {
        return RestIllegalArgumentJson.builder()
                .url(request.getRequestURI())
                .message(exception.getMessage())
                .build();
    }

}
