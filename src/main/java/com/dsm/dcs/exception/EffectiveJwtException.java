package com.dsm.dcs.exception;

import com.dsm.dcs.error.exception.DcsException;
import com.dsm.dcs.error.exception.ErrorCode;

public class EffectiveJwtException extends DcsException {

    public static final EffectiveJwtException EXCEPTION =
            new EffectiveJwtException();

    private EffectiveJwtException() {
        super(ErrorCode.EFFECTIVE_JWT);
    }

}