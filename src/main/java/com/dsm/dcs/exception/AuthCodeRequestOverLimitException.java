package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class AuthCodeRequestOverLimitException extends DcsException {

    public static final AuthCodeRequestOverLimitException EXCEPTION =
            new AuthCodeRequestOverLimitException();

    private AuthCodeRequestOverLimitException() {
        super(ErrorCode.AUTH_CODE_REQUEST_OVER_LIMIT);
    }
}
