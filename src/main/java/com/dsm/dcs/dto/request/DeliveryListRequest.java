package com.dsm.dcs.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class DeliveryListRequest {

    @NotBlank
    private String courierCompany;

    List<DeliveryDto> delivery = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    public class DeliveryDto {

        @NotBlank
        @Size(max = 10)
        private String recipientName;

        @NotBlank
        @Size(max = 11)
        private String recipientPhoneNumber;

        @NotBlank
        @Size(max = 30)
        private String product;

    }

}


