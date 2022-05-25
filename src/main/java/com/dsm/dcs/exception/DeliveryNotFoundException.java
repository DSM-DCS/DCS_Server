package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class DeliveryNotFoundException extends DcsException {

    public static final DeliveryNotFoundException EXCEPTION =
            new DeliveryNotFoundException();

    private DeliveryNotFoundException() {
        super(404, "Delivery Not Found");
    }
}
