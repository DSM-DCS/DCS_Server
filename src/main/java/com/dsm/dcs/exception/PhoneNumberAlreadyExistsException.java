package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class PhoneNumberAlreadyExistsException extends DcsException {

    public static final PhoneNumberAlreadyExistsException EXCEPTION =
            new PhoneNumberAlreadyExistsException();

    private PhoneNumberAlreadyExistsException() {
        super(409, "PhoneNumber already exists");
    }
}
