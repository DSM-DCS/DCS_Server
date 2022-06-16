package com.dsm.dcs.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationPasswordRequest {

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String password;
}
