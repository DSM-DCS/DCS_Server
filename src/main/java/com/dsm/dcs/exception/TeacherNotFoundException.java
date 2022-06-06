package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class TeacherNotFoundException extends DcsException {

    public static final TeacherNotFoundException EXCEPTION =
            new TeacherNotFoundException();

    private TeacherNotFoundException() {
        super(404, "Teacher Not Found");
    }
}
