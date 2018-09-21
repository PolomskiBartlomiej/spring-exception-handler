package com.demo.spring.errorhandler.infrastructure.soap.error;

class SoapIllegalArgumentExceptionHandler extends SoapFaultHandler {

    SoapIllegalArgumentExceptionHandler(Exception exception) {
        super(exception);
    }

    @Override
    protected String errorCode() {
        return "BAD_REQUEST";
    }

}
