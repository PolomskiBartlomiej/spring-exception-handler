package com.demo.spring.errorhandler.infrastructure.soap.config;

import com.demo.spring.errorhandler.infrastructure.soap.NameSpace;
import com.demo.spring.errorhandler.infrastructure.soap.error.SoapFaultHandler;
import com.demo.spring.errorhandler.infrastructure.soap.interceptor.SoapExceptionInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

@Configuration
@EnableWs
class WebServiceConfig {

    @Bean SoapFaultMappingExceptionResolver
    exceptionResolver() {
        SoapFaultMappingExceptionResolver exceptionResolver = new SoapExceptionInterceptor();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(SoapFaultHandler.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }


    @Bean ServletRegistrationBean
    messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "orders") DefaultWsdl11Definition
    defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("OrdersPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NameSpace.ORDER);
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean XsdSchema
    countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("orders.xsd"));
    }
}
