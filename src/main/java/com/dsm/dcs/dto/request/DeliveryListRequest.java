package com.dsm.dcs.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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

        @NotNull
        private String phoneNumber;

    }

}


