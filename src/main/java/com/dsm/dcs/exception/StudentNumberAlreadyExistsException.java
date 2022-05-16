package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class StudentNumberAlreadyExistsException extends DcsException {

    public static final StudentNumberAlreadyExistsException EXCEPTION =
            new StudentNumberAlreadyExistsException();

    private StudentNumberAlreadyExistsException() {
        super(409, "StudentNumber already exists");
    }
}
