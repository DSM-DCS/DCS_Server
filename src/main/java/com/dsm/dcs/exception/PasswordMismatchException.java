package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class PasswordMismatchException extends DcsException {

    public static final PasswordMismatchException EXCEPTION =
            new PasswordMismatchException();

    private PasswordMismatchException() {
        super(401, "Password Mismatch");
    }
}
