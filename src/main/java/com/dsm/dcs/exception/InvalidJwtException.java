package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class InvalidJwtException extends DcsException {

    public static final InvalidJwtException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(401, "Invalid Token");
    }
}
