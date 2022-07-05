package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class ForbiddenException extends DcsException {

    public static final ForbiddenException EXCEPTION =
            new ForbiddenException();

    private ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
