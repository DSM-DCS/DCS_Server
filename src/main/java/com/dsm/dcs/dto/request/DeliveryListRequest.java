package com.dsm.dcs.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class DeliveryListRequest {

    @NotNull
    private Integer couriercompany;

    private List<DeliveryWaybillNumberRequest> deliverywaybillnumberrequestlist;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryWaybillNumberRequest {

        @NotNull
        private BigInteger waybillnumber;

    }

}


