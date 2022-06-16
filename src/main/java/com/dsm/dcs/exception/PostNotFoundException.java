package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class PostNotFoundException extends DcsException {

    public static final PostNotFoundException EXCEPTION =
            new PostNotFoundException();

    private PostNotFoundException() {
        super(404, "Post Not Found");
    }

}
