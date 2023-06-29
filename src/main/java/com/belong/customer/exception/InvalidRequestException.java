package com.belong.customer.exception;

import org.springframework.validation.Errors;

public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = -5147030042014002679L;

    private final transient Errors errors;

    public InvalidRequestException(final Errors errors) {

        this.errors = errors;
    }

    public Errors getErrors() {

        return errors;
    }

}
