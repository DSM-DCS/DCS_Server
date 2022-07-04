package com.dsm.dcs.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class FindPasswordRequest {

    @NotBlank(message = "email은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Email(regexp = "^([[~!@#$%^&*()-_.]?0-9a-zA-Z])+@[dsm]+.[hs]+.kr$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
