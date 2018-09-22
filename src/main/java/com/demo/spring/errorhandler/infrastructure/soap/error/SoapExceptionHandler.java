package com.demo.spring.errorhandler.infrastructure.soap.error;

class SoapExceptionHandler extends SoapFaultHandler {

    SoapExceptionHandler(Exception exception) {
        super(exception);
    }

    @Override
    String errorCode() {
        return "ERROR";
    }

    @Override
    String message() {
        return "Something go wrong";
    }
}
