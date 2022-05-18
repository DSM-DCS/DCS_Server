package com.dsm.dcs.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class DeliveryListRequest {

    @NotNull
    private Integer couriercompany;

    private List<PhoneNumberRequest> phoneNumberRequestList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneNumberRequest {

        @NotNull
        private String phoneNumber;

    }

}


