package com.demo.spring.errorhandler.infrastructure.soap.error;

class SoapExceptionHandler extends SoapFaultHandler {

    SoapExceptionHandler(Exception exception) {
        super(exception);
    }

    @Override
    protected String errorCode() {
        return "ERROR";
    }

    @Override
    protected String message() {
        return "Something go wrong";
    }
}
