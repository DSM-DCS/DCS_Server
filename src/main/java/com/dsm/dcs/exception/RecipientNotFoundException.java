package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class RecipientNotFoundException extends DcsException {

    public static final RecipientNotFoundException EXCEPTION =
            new RecipientNotFoundException();

    private RecipientNotFoundException() {
        super(ErrorCode.RECIPIENT_NOT_FOUND);
    }
}
