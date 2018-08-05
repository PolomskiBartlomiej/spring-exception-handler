package com.demo.spring.errorhandler.infrastructure.rest.interceptor;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class RestServiceErrorJson {
    private String url;
    private String message;
}
