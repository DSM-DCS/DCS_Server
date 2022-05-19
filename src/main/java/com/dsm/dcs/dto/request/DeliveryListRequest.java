package com.dsm.dcs.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@NoArgsConstructor
public class DeliveryListRequest {

    @NotBlank
    private String couriercompany;

    private List<PhoneNumberRequest> phoneNumberRequestList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneNumberRequest {

        @NotBlank
        @Pattern(regexp = "^010 -+\\d{4}-+\\d{4}$")
        private String phoneNumber;

    }

}


