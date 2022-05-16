package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class AccountIdAlreadyExistsException extends DcsException {

    public static final AccountIdAlreadyExistsException EXCEPTION =
            new AccountIdAlreadyExistsException();

    private AccountIdAlreadyExistsException() {
        super(409, "accountId already exists");
    }
}
