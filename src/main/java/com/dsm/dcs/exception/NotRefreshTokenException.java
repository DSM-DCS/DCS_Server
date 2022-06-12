package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class NotRefreshTokenException extends DcsException {

    public static final  NotRefreshTokenException EXCEPTION =
            new  NotRefreshTokenException();

    private  NotRefreshTokenException() {
        super(401, "Not a refresh token");
    }

}
