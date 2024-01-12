package com.jumpfish.developer.porjects.monitors.authority.exception;

import org.springframework.security.authentication.AccountStatusException;


public class UserAuthenticationException extends AccountStatusException {
    public UserAuthenticationException(String msg) {
        super(msg);
    }

    public UserAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}
