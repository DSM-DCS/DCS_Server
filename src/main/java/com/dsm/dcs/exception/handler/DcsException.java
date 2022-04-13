package com.dsm.dcs.exception.handler;

import lombok.Getter;

@Getter
public class DcsException extends RuntimeException {

    private final int status;
    private final String message;

    protected DcsException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
