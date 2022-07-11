package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class PhoneNumberNotMatchException extends DcsException {

    public static final PhoneNumberNotMatchException EXCEPTION =
            new PhoneNumberNotMatchException();

    private PhoneNumberNotMatchException() {
        super(ErrorCode.PHONE_NUMBER_NOTMATCH);
    }
}
