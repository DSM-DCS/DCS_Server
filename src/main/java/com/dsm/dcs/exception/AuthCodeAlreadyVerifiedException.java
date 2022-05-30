package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class AuthCodeAlreadyVerifiedException extends DcsException {

    public static final AuthCodeAlreadyVerifiedException EXCEPTION =
            new AuthCodeAlreadyVerifiedException();

    private AuthCodeAlreadyVerifiedException() {
        super(404, "AuthCode already exists");
    }
}
