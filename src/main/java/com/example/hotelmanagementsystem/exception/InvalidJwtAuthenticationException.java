package com.example.hotelmanagementsystem.exception;

import org.springframework.security.core.AuthenticationException;

/*
Fahim created at 11/15/2020
*/
public class InvalidJwtAuthenticationException extends AuthenticationException {
    /**
     *
     */
    private static final long serialVersionUID = -761503632186596342L;

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
