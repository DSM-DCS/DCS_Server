package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class CourierNotFoundException extends DcsException {

    public static final CourierNotFoundException EXCEPTION =
            new CourierNotFoundException();

    private CourierNotFoundException() {
        super(404, "Courier Not Found");
    }
}
