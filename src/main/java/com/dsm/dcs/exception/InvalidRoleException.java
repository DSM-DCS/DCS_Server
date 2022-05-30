package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class InvalidRoleException extends DcsException {

    public static final InvalidRoleException EXCEPTION =
            new InvalidRoleException();

    private InvalidRoleException() {
        super(401, "Invalid Role");
    }
}
