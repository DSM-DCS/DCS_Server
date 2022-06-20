package com.dsm.dcs.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DcsException extends RuntimeException {

    private final ErrorCode errorCode;
}
