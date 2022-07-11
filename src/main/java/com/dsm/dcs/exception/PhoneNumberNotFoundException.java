package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class PhoneNumberNotFoundException extends DcsException {

    public static final PhoneNumberNotFoundException EXCEPTION =
            new PhoneNumberNotFoundException();

    private PhoneNumberNotFoundException() {
        super(ErrorCode.PHONE_NUMBER_NOTFOUND);
    }
}
