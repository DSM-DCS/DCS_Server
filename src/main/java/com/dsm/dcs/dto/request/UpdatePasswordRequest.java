package com.dsm.dcs.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "email은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Email(regexp = "^([[~!@#$%^&*()-_.]?0-9a-zA-Z])+@[dsm]+.[hs]+.kr$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "new_password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" +
            "<=>?@＼^_`{|}~]{8,30}$",
            message = "new_password는 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String newPassword;
}
