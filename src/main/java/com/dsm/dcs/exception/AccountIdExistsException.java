package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class AccountIdExistsException extends DcsException {

    public static final AccountIdExistsException EXCEPTION =
            new AccountIdExistsException();

    private AccountIdExistsException() {
        super(ErrorCode.ACCOUNT_ID_EXISTS);
    }
}
