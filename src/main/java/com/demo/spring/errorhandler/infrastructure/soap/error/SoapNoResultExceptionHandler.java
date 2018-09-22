package com.demo.spring.errorhandler.infrastructure.soap.error;

class SoapNoResultExceptionHandler extends SoapFaultHandler {

    SoapNoResultExceptionHandler(Exception exception) {
        super(exception);
    }

    @Override
    String errorCode() {
        return "NO_RESULT";
    }
}
