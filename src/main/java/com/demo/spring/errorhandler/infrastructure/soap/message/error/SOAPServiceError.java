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
@Setter
@NoArgsConstructor
public class SOAPServiceError {

    public SOAPServiceError(Exception exception) {
        this.code = "ERROR";
        this.message = "Something go wrong";

        if (exception instanceof NoResultException) {
            this.code = "NO_RESULT";
            this.message = exception.getMessage();
        }

        if (exception instanceof IllegalArgumentException) {
            this.code = "BAD_REQUEST";
            this.message = exception.getMessage();
        }
    }

    public static final QName Q_CODE = new QName("code");
    public static final QName Q_MESSAGE = new QName("message");

    private String code;
    private String message;

}
