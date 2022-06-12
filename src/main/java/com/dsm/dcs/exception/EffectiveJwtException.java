package com.dsm.dcs.exception;

import com.dsm.dcs.exception.handler.DcsException;

public class EffectiveJwtException extends DcsException {

    public static final EffectiveJwtException EXCEPTION =
            new EffectiveJwtException();

    private EffectiveJwtException() {
        super(401, "Effective Access Token");
    }

}