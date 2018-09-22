package com.demo.spring.errorhandler.infrastructure.soap.error;


import com.demo.spring.errorhandler.domain.order.exception.NoResultException;

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
