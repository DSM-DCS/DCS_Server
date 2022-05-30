package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class UserAlreadyExistsException extends DcsException {

    public static final UserAlreadyExistsException EXCEPTION =
            new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(409, "User already exists");
    }
}
