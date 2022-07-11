package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class AuthCodeNotMatchExceptio extends DcsException {

    public static final AuthCodeNotMatchExceptio EXCEPTION =
            new AuthCodeNotMatchExceptio();

    private AuthCodeNotMatchExceptio() {
        super(ErrorCode.AUTH_CODE_NOT_MATCH);
    }
}
