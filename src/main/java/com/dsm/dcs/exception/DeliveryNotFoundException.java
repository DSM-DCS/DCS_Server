package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class DeliveryNotFoundException extends DcsException {

    public static final DeliveryNotFoundException EXCEPTION =
            new DeliveryNotFoundException();

    private DeliveryNotFoundException() {
        super(ErrorCode.DELIVERY_NOT_FOUND);
    }
}
