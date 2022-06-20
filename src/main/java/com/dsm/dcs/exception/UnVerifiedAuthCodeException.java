package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class UnVerifiedAuthCodeException extends DcsException {

    public static final UnVerifiedAuthCodeException EXCEPTION =
            new UnVerifiedAuthCodeException();

    private UnVerifiedAuthCodeException() {
        super(ErrorCode.UNVERIFIED_AUTH_CODE);
    }
}
