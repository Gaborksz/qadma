package com.practise.qadma.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJWTokenException extends AuthenticationException {

    public InvalidJWTokenException() {
        super("Token could not be verified");
    }
}
