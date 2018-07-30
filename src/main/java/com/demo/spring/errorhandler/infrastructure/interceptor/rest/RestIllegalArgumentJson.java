package com.demo.spring.errorhandler.infrastructure.interceptor.rest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class RestIllegalArgumentJson {
    private String url;
    private String message;
}
