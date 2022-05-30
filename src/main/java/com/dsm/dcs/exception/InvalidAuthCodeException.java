package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class InvalidAuthCodeException extends DcsException {

    public static final InvalidAuthCodeException EXCEPTION =
            new InvalidAuthCodeException();

    private InvalidAuthCodeException() {
        super(401, "Invalid AuthCode");
    }

}
