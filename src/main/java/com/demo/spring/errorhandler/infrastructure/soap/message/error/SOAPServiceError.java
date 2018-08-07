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
        return new SOAPServiceError(getCode(ex),getMessage(ex));
    }

    private SOAPServiceError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private static String getCode(Exception e) {
        if (e instanceof NoResultException) return "NO_RESULT";
        if (e instanceof IllegalArgumentException) return "BAD_REQUEST";
        return "ERROR";
    }

    private static String getMessage(Exception e) {
        if (e instanceof NoResultException) return e.getLocalizedMessage();
        if (e instanceof IllegalArgumentException) return e.getLocalizedMessage();
        return "Something go wrong";
    }

    private String code;
    private String message;
}
