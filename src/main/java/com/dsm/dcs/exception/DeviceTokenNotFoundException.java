package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class DeviceTokenNotFoundException extends DcsException {

    public static final DeviceTokenNotFoundException EXCEPTION =
            new DeviceTokenNotFoundException();

    private DeviceTokenNotFoundException() {
        super(404, "DeviceToken Not Found");
    }
}
