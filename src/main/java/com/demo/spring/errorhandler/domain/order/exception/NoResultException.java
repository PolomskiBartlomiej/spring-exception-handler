package com.demo.spring.errorhandler.domain.order.exception;

public class NoResultException extends RuntimeException {
    public NoResultException(String massage) {
        super(massage);
    }
}
