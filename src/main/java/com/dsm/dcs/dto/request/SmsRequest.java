package com.dsm.dcs.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsRequest {

    @NotBlank(message = "phone_number은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다")
    @Pattern(regexp = "^010\\d{8}$")
    private String phoneNumber;

    @NotBlank
    private String code;

}
