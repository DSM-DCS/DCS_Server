package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class RefreshTokenNotFoundException extends DcsException {

    public static final RefreshTokenNotFoundException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(404, "RefreshToken Not Found");
    }
}
