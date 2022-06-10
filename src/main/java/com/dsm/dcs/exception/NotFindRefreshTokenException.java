package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

import java.util.function.Supplier;

public class NotFindRefreshTokenException extends DcsException {

    public static final  NotFindRefreshTokenException EXCEPTION =
            new  NotFindRefreshTokenException();

    private  NotFindRefreshTokenException() {
        super(401, "Not Find Refresh Token");
    }

}
