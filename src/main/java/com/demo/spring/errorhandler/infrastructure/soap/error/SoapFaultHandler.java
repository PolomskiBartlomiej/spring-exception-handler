package com.demo.spring.errorhandler.infrastructure.soap.error;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;

import javax.xml.namespace.QName;

@RequiredArgsConstructor
public abstract class SoapFaultHandler {
    private static final QName Q_CODE = new QName("errorCode");
    private static final QName Q_MESSAGE = new QName("message");

    private final Exception exception;

    protected abstract String errorCode();

    protected String message() {
       return   exception.getLocalizedMessage();
    }

    public void addFaultDetail(SoapFault fault) {
        SoapFaultDetail soapFaultDetail = fault.addFaultDetail();
        soapFaultDetail.addFaultDetailElement(Q_CODE)
                .addText(errorCode());
        soapFaultDetail.addFaultDetailElement(Q_MESSAGE)
                .addText(message());
    }
}
