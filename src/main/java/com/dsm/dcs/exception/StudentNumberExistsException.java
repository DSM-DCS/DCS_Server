package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class StudentNumberExistsException extends DcsException {

    public static final StudentNumberExistsException EXCEPTION =
            new StudentNumberExistsException();

    private StudentNumberExistsException() {
        super(ErrorCode.STUDENT_NUMBER_EXISTS);
    }
}
