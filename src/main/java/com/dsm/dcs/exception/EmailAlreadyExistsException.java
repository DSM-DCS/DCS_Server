package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class EmailAlreadyExistsException extends DcsException {

    public static final EmailAlreadyExistsException EXCEPTION =
            new EmailAlreadyExistsException();

    private EmailAlreadyExistsException() {
        super(409, "email already exists");
    }
}
