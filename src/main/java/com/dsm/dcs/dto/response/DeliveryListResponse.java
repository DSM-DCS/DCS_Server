package com.dsm.dcs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DeliveryListResponse {

    List<DeliveryResponse> deliveryResponses;

    @Getter
    @Builder
    public static class DeliveryResponse {

        private final Long id;
        private final String courierCompany;
        private final String name;
        private final LocalDate createdDate;

    }

}
