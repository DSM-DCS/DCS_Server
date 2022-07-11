package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class AuthCodeNotMatchException extends DcsException {

    public static final AuthCodeNotMatchException EXCEPTION =
            new AuthCodeNotMatchException();

    private AuthCodeNotMatchException() {
        super(ErrorCode.AUTH_CODE_NOT_MATCH);
    }
}
