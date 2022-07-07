package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class AdminNotFoundException extends DcsException {

    public static final AdminNotFoundException EXCEPTION =
            new AdminNotFoundException();

    private AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
