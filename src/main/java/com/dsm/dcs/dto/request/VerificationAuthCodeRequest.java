package com.dsm.dcs.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class VerificationAuthCodeRequest {

    @NotBlank(message = "email은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Email(regexp = "^([[~!@#$%^&*()-_.]?0-9a-zA-Z])+@[dsm]+.[hs]+.kr$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Length(min = 6, max = 6, message = "code는 6글자여야 합니다.")
    private String code;
}
