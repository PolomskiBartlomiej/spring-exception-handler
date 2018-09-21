package com.demo.spring.errorhandler.infrastructure.soap.error;

import javax.persistence.NoResultException;

public class SoapFaultHandlerFactory {
    public static SoapFaultHandler of(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            return new SoapIllegalArgumentExceptionHandler(ex);
        }
        if (ex instanceof NoResultException) {
            return new SoapNoResultExceptionHandler(ex);
        }
        return new SoapExceptionHandler(ex);
    }
}
