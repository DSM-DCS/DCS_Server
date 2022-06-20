package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class AuthCodeAlreadyVerifiedException extends DcsException {

    public static final AuthCodeAlreadyVerifiedException EXCEPTION =
            new AuthCodeAlreadyVerifiedException();

    private AuthCodeAlreadyVerifiedException() {
        super(ErrorCode.USER_AUTH_CODE_ALREADY_VERIFIED);
    }
}
