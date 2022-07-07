package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class PhoneNumberExistsException extends DcsException {

    public static final PhoneNumberExistsException EXCEPTION =
            new PhoneNumberExistsException();

    private PhoneNumberExistsException() {
        super(ErrorCode.PHONE_NUMBER_EXISTS);
    }
}
