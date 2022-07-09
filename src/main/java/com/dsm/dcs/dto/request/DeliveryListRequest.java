package com.dsm.dcs.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryListRequest {

    @NotBlank
    private String couriercompany;

    private List<PhoneNumberRequest> phoneNumberRequestList;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PhoneNumberRequest {

        @NotBlank
        @Pattern(regexp = "^010-+\\d{4}-+\\d{4}$")
        private String phoneNumber;

        @NotBlank
        @Size(max = 20)
        private String products;

    }

}


