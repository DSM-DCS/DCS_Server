package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class UserAuthCodeNotFoundException extends DcsException {

    public static final UserAuthCodeNotFoundException EXCEPTION =
            new UserAuthCodeNotFoundException();

    private UserAuthCodeNotFoundException() {
        super(ErrorCode.USER_AUTH_CODE_NOT_FOUND);
    }
}
