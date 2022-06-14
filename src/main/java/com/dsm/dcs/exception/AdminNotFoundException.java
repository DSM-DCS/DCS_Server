package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class AdminNotFoundException extends DcsException {

    public static final AdminNotFoundException EXCEPTION =
            new AdminNotFoundException();

    private AdminNotFoundException() {
        super(404, "Admin Not Found");
    }
}
