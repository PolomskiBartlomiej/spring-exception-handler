package com.demo.spring.errorhandler.infrastructure.soap.interceptor;

import com.demo.spring.errorhandler.infrastructure.soap.error.SoapFaultHandler;
import com.demo.spring.errorhandler.infrastructure.soap.error.SoapFaultHandlerFactory;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;


public class SoapExceptionInterceptor extends SoapFaultMappingExceptionResolver {

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
            logger.warn(ex);
            SoapFaultHandler handler = SoapFaultHandlerFactory.of(ex);
            handler.addFaultDetail(fault);
    }
}
