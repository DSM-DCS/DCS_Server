package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class FireBaseException extends DcsException {

    public static final FireBaseException EXCEPTION =
            new FireBaseException();

    private FireBaseException() {
        super(ErrorCode.FIREBASE_EXCEPTION);
    }
}
