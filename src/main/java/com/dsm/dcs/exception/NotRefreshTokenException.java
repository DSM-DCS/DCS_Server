package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class NotRefreshTokenException extends DcsException {

    public static final  NotRefreshTokenException EXCEPTION =
            new  NotRefreshTokenException();

    private  NotRefreshTokenException() {
        super(ErrorCode.NOT_REFRESH_TOKEN);
    }

}
