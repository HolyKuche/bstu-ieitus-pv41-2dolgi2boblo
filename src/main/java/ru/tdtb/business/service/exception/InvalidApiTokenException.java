package ru.tdtb.business.service.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidApiTokenException extends BadCredentialsException {

    public InvalidApiTokenException(String token) {
        super("Invalid token: " + token);
    }
}