package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class FireBaseException extends DcsException {

    public static final FireBaseException EXCEPTION =
            new FireBaseException();

    private FireBaseException() {
        super(409, "알림을 성공적으로 전송하지 못함");
    }
}
