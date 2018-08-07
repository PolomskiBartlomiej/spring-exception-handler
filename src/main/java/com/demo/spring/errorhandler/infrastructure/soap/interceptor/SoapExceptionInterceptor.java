package com.demo.spring.errorhandler.infrastructure.soap.interceptor;

import com.demo.spring.errorhandler.infrastructure.soap.message.error.SOAPServiceError;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;


public class SoapExceptionInterceptor extends SoapFaultMappingExceptionResolver {

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
            logger.warn(ex);
            SOAPServiceError serviceError = SOAPServiceError.of(ex);
            SoapFaultDetail soapFaultDetail = fault.addFaultDetail();
            soapFaultDetail.addFaultDetailElement(SOAPServiceError.Q_CODE)
                    .addText(serviceError.getCode());
            soapFaultDetail.addFaultDetailElement(SOAPServiceError.Q_MESSAGE)
                    .addText(serviceError.getMessage());
    }


}
