package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class UnVerifiedAuthCodeException extends DcsException {

    public static final UnVerifiedAuthCodeException EXCEPTION =
            new UnVerifiedAuthCodeException();

    private UnVerifiedAuthCodeException() {
        super(401, "UnVerified Auth Code");
    }
}
