package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class AuthCodeRequestOverLimitException extends DcsException {

    public static final AuthCodeRequestOverLimitException EXCEPTION =
            new AuthCodeRequestOverLimitException();

    private AuthCodeRequestOverLimitException() {
        super(429, "Auth Code Request Over Limit");
    }
}
