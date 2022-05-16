package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class UserNotFoundException extends DcsException {

    public static final UserNotFoundException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(404, "User Not Found");
    }
}
