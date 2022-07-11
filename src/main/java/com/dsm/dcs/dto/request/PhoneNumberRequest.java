package com.dsm.dcs.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumberRequest {

    @NotBlank
    @Pattern(regexp = "^010-+\\d{4}-+\\d{4}$")
    private String phoneNumber;

}
