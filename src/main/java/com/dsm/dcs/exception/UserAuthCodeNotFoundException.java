package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class UserAuthCodeNotFoundException extends DcsException {

    public static final UserAuthCodeNotFoundException EXCEPTION =
            new UserAuthCodeNotFoundException();

    private UserAuthCodeNotFoundException() {
        super(404, "User AuthCode Not Found");
    }
}
