package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class EmailExistsException extends DcsException {

    public static final EmailExistsException EXCEPTION =
            new EmailExistsException();

    private EmailExistsException() {
        super(ErrorCode.EMAIL_EXISTS);
    }
}
