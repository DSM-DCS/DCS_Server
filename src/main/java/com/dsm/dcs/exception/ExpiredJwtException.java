package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class ExpiredJwtException extends DcsException {

    public static final ExpiredJwtException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(401, "Expired Jwt");
    }
}
