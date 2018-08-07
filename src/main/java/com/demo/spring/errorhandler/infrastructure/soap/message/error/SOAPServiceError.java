package com.demo.spring.errorhandler.infrastructure.soap.message.error;

import lombok.*;

import javax.persistence.NoResultException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFault", propOrder = {
        "code",
        "message"
})
@Getter
@NoArgsConstructor
public class SOAPServiceError {

    public static final QName Q_CODE = new QName("code");
    public static final QName Q_MESSAGE = new QName("message");

    public static SOAPServiceError of(Exception ex) {

        if (ex instanceof NoResultException) {
           return new SOAPServiceError("NO_RESULT",ex.getLocalizedMessage());
        }

        if (ex instanceof IllegalArgumentException) {
            return new SOAPServiceError("BAD_REQUEST",ex.getLocalizedMessage());
        }

        return new SOAPServiceError("ERROR","Something go wrong");
    }

    private SOAPServiceError(String code, String message) {
        this.code = code;
        this.message = message;
    }


    private String code;
    private String message;

}
