package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class PasswordMismatchException extends DcsException {

    public static final PasswordMismatchException EXCEPTION =
            new PasswordMismatchException();

    private PasswordMismatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }
}
