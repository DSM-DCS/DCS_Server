package com.dsm.dcs.exception;


import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class DeviceTokenNotFoundException extends DcsException {

    public static final DeviceTokenNotFoundException EXCEPTION =
            new DeviceTokenNotFoundException();

    private DeviceTokenNotFoundException() {
        super(ErrorCode.DEVICE_TOKEN_NOT_FOUND);
    }
}
