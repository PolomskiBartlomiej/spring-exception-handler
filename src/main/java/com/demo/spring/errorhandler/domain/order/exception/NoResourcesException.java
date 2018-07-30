package com.demo.spring.errorhandler.domain.order.exception;


import org.apache.commons.lang3.StringUtils;

public class NoResourcesException extends RuntimeException {

    public NoResourcesException(Object s) {
        super("No resources for " + StringUtils.defaultString(s.toString(),""));
    }
}
