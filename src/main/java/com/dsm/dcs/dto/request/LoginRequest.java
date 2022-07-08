package com.dsm.dcs.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotBlank(message = "account_id는 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 6, max = 20, message = "account_id는 6글자 이상, 20글자 이하여야 합니다.")
    private String accountId;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String password;

    private String deviceToken;
}
